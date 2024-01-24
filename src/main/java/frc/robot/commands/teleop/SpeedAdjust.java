package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSub;

public class SpeedAdjust extends Command {
    private final Joystick joystick;
    private final DriveTrainSub driveSub;

    public SpeedAdjust(DriveTrainSub driveSub, Joystick joystick) {
        this.driveSub = driveSub;
        this.joystick = joystick;
    }

    @Override
    public void execute() {
        this.driveSub.setSpeed(this.joystick.getRawAxis(4));
    }
}
