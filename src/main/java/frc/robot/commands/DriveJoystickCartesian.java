package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveTrainSub;

public class DriveJoystickCartesian extends Command {

    private final DriveTrainSub drive;
    private final Joystick joystick;
    JoystickButton butt11;

    public DriveJoystickCartesian(
            DriveTrainSub subsystem,
            Joystick joystick
    ) {
        this.joystick = joystick;
        drive = subsystem;
        butt11 = new JoystickButton(joystick, 11);
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.mecanumDrive(
                joystick.getRawAxis(1),
                joystick.getRawAxis(0),
                joystick.getRawAxis(2)
        );
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
