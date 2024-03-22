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
import frc.robot.commands.autoCommands.ShootAndRunCenter;
import frc.robot.commands.teleop.TeleopDriveCartesian;
import frc.robot.commands.teleop.climb.ClimbDown;
import frc.robot.commands.teleop.climb.ClimbStop;
import frc.robot.commands.teleop.climb.ClimbUp;
import frc.robot.commands.teleop.climb.leftClimb.ClimbDownLeft;
import frc.robot.commands.teleop.climb.leftClimb.ClimbStopLeft;
import frc.robot.commands.teleop.climb.leftClimb.ClimbUpLeft;
import frc.robot.commands.teleop.climb.rightClimb.ClimbDownRight;
import frc.robot.commands.teleop.climb.rightClimb.ClimbStopRight;
import frc.robot.commands.teleop.climb.rightClimb.ClimbUpRight;
import frc.robot.commands.teleop.feeder.FeederInward;
import frc.robot.commands.teleop.feeder.FeederOutward;
import frc.robot.commands.teleop.feeder.FeederStop;
import frc.robot.commands.teleop.flywheel.FlywheelsInward;
import frc.robot.commands.teleop.flywheel.FlywheelsOutward;
import frc.robot.commands.teleop.flywheel.FlywheelsStop;
import frc.robot.commands.teleop.intake.TeleopDeploy;
import frc.robot.commands.teleop.intake.TeleopRetract;
import frc.robot.commands.teleop.intake.TeleopStopDeploy;
import frc.robot.subsystems.ClimbSub;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.ShooterSub;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrainSub driveSub;
    private final IntakeSub intakeSub;
    private final ShooterSub shooterSub;
    private final ClimbSub climbSub;
    Joystick leftFlightStick = new Joystick(Constants.Port.SECONDARY_JOYSTICK);
    Joystick rightFlightStick = new Joystick(Constants.Port.MAIN_JOYSTICK);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        climbSub = new ClimbSub();
        driveSub = new DriveTrainSub();
        intakeSub = new IntakeSub();
        shooterSub = new ShooterSub();
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
                rightFlightStick.getName()
        );

        driveSub.setDefaultCommand(
                new TeleopDriveCartesian(
                        driveSub,
                        rightFlightStick
                )
        );
          intakeSub.setDefaultCommand(new TeleopStopDeploy(intakeSub));



        //Intake
        final JoystickButton intakeButton = new JoystickButton(rightFlightStick, Constants.RightButtons.INTAKE);
        intakeButton.onTrue(new RunCommand(intakeSub::intake, intakeSub));
        intakeButton.onFalse(new RunCommand(intakeSub::stopIntake, intakeSub));

        final JoystickButton ejectButton = new JoystickButton(rightFlightStick, Constants.RightButtons.EJECT);
        ejectButton.onTrue(new RunCommand(intakeSub::eject, intakeSub));
        ejectButton.onFalse(new RunCommand(intakeSub::stopIntake, intakeSub));

        //Intake Tower
        final JoystickButton riseButton = new JoystickButton(rightFlightStick, Constants.RightButtons.RISE);
        riseButton.onTrue(new RunCommand(intakeSub::intakeTowerRise, intakeSub));
        riseButton.onFalse(new RunCommand(intakeSub::intakeTowerStop, intakeSub));

        final JoystickButton dropButton = new JoystickButton(rightFlightStick, Constants.RightButtons.DROP);
        dropButton.onTrue(new RunCommand(intakeSub::intakeTowerDrop, intakeSub));
        dropButton.onFalse(new RunCommand(intakeSub::intakeTowerStop, intakeSub));

        //Intake hinge
        final JoystickButton deployButton = new JoystickButton(rightFlightStick, Constants.RightButtons.DEPLOY);
        deployButton.onTrue(new TeleopDeploy(intakeSub));
        deployButton.onFalse(new TeleopStopDeploy(intakeSub));

        final JoystickButton retractButton = new JoystickButton(rightFlightStick, Constants.RightButtons.RETRACT);
        retractButton.onTrue(new TeleopRetract(intakeSub));
        retractButton.onFalse(new TeleopStopDeploy(intakeSub));

        final JoystickButton feederIn = new JoystickButton(leftFlightStick, Constants.LeftButtons.FEEDER_IN);
        feederIn.onTrue(new FeederInward(shooterSub));
        feederIn.onFalse(new FeederStop(shooterSub));

        final JoystickButton feederOutButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.FEEDER_OUT);
        feederOutButton.onTrue(new FeederOutward(shooterSub));
        feederOutButton.onFalse(new FeederStop(shooterSub));

        final JoystickButton shooterIn = new JoystickButton(leftFlightStick, Constants.LeftButtons.SLURP);
        shooterIn.onTrue(new FlywheelsInward(shooterSub));
        shooterIn.onFalse(new FlywheelsStop(shooterSub));

        final JoystickButton shootButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.SPIT);
        shootButton.onTrue(new FlywheelsOutward(shooterSub));
        shootButton.onFalse(new FlywheelsStop(shooterSub));

        final JoystickButton climbUpButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.UPPIES);
        climbUpButton.onTrue(new ClimbUp(climbSub));
        climbUpButton.onFalse(new ClimbStop(climbSub));

        final JoystickButton climbDownButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.DOWNS);
        climbDownButton.onTrue(new ClimbDown(climbSub));
        climbDownButton.onFalse(new ClimbStop(climbSub));

        final JoystickButton leftClimbDownButt = new JoystickButton(leftFlightStick, Constants.LeftButtons.LEFT_DOWNS);
        leftClimbDownButt.onTrue(new ClimbDownLeft(climbSub));
        leftClimbDownButt.onFalse(new ClimbStopLeft(climbSub));

        final JoystickButton leftClimbUpButt = new JoystickButton(leftFlightStick, Constants.LeftButtons.LEFT_UPPIES);
        leftClimbUpButt.onTrue(new ClimbUpLeft(climbSub));
        leftClimbUpButt.onFalse(new ClimbStopLeft(climbSub));

        final JoystickButton rightClimbDownButt = new JoystickButton(leftFlightStick, Constants.LeftButtons.RIGHT_DOWNS);
        rightClimbDownButt.onTrue(new ClimbDownRight(climbSub));
        rightClimbDownButt.onFalse(new ClimbStopRight(climbSub));

        final JoystickButton rightClimbUpButt = new JoystickButton(leftFlightStick, Constants.LeftButtons.RIGHT_UPPIES);
        rightClimbUpButt.onTrue(new ClimbUpRight(climbSub));
        rightClimbUpButt.onFalse(new ClimbStopRight(climbSub));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new ShootAndRunCenter(shooterSub, driveSub);
    }
}
