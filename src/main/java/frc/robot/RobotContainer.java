// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.flywheel.FlywheelsInward;
import frc.robot.commands.flywheel.FlywheelsOutward;
import frc.robot.commands.flywheel.FlywheelsStop;
import frc.robot.commands.teleop.TeleopDriveCartesian;
import frc.robot.commands.teleop.intake.TeleopDeploy;
import frc.robot.commands.teleop.intake.TeleopRetract;
import frc.robot.commands.teleop.intake.TeleopStopDeploy;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.ShooterSub;
import jdk.jshell.execution.JdiExecutionControlProvider;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
//    private final DriveTrainSub driveSub;
    private final IntakeSub intakeSub;
//    private final ShooterSub shooterSub;
    //    Joystick flightStickControl = new Joystick(1);
    Joystick flightStickDrive = new Joystick(Constants.Port.MAIN_JOYSTICK);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {


//        driveSub = new DriveTrainSub();
        intakeSub = new IntakeSub();
//        shooterSub = new ShooterSub();
        configureBindings();
    }


    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        System.out.println(
                "Joystick name: " +
                flightStickDrive.getName()
        );

/*

        driveSub.setDefaultCommand(
                new TeleopDriveCartesian(
                        driveSub,
                        flightStickDrive
                )
        );
          intakeSub.setDefaultCommand(new TeleopStopDeploy(intakeSub));
          */


        final JoystickButton intakeButton = new JoystickButton(flightStickDrive, Constants.Buttons.INTAKE);
        intakeButton.onTrue(new RunCommand(intakeSub::intake, intakeSub));
        intakeButton.onFalse(new RunCommand(intakeSub::stopIntake, intakeSub));

        final JoystickButton ejectButton = new JoystickButton(flightStickDrive, Constants.Buttons.EJECT);
        ejectButton.onTrue(new RunCommand(intakeSub::eject, intakeSub));
        ejectButton.onFalse(new RunCommand(intakeSub::stopIntake, intakeSub));

        final JoystickButton deployButton = new JoystickButton(flightStickDrive, Constants.Buttons.DEPLOY);
        deployButton.onTrue(new TeleopDeploy(intakeSub));
        deployButton.onFalse(new TeleopStopDeploy(intakeSub));

        final JoystickButton retractButton = new JoystickButton(flightStickDrive, Constants.Buttons.RETRACT);
        retractButton.onTrue(new TeleopRetract(intakeSub));
        retractButton.onFalse(new TeleopStopDeploy(intakeSub));


//        JoystickButton butt7 = new JoystickButton(flightStickDrive, 7);
//        JoystickButton butt8 = new JoystickButton(flightStickDrive, 8);
//
//        butt7.onTrue(new FlywheelsInward(shooterSub));
//        butt7.onFalse(new FlywheelsStop(shooterSub));
//
//        butt8.onTrue(new FlywheelsOutward(shooterSub));
//        butt8.onFalse(new FlywheelsStop(shooterSub));

        JoystickButton butt9 = new JoystickButton(flightStickDrive, 9);
        JoystickButton butt10 = new JoystickButton(flightStickDrive, 10);


    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
