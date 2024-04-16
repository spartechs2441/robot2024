package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;
import frc.robot.utils.StrafeDirection;

public class AutoDriveRotation extends Command {
    private final DriveTrainSub drive;
    private final int direction;

    /**
     * @param subsystem       The subsystem that the robot uses to drive
     * @param direction       0 for left, 1 for right
     */
    public AutoDriveRotation(DriveTrainSub subsystem, int direction) {
        drive = subsystem;
        addRequirements(drive);
        this.direction = direction;
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        double rotationSpeed = 0;
        double angle = 0;
        if(direction == 0){
            if(angle > 315) {
                drive.mecanumDrive(0, 0, -rotationSpeed);
            }
        }
        if(direction == 1) {
            if (angle < 45) {
                drive.mecanumDrive(0, 0, rotationSpeed);
            }
        }
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        drive.mecanumDrive(0, 0, 0);
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}
