package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;
import frc.robot.utils.StrafeDirection;

public class AutoDriveWhileIntake extends Command {
    private final DriveTrainSub drive;
    private final IntakeSub intake;
    private final CustomaryLength distance;
    private final StrafeDirection strafeDirection;
    //private double startTime;
    //private double duration;

    /**
     * @param subsystem       The subsystem that the robot uses to drive
     * @param distance        Distance to travel
     * @param strafeDirection Direction robot will move
     */
    public AutoDriveWhileIntake(IntakeSub intakeSub, DriveTrainSub subsystem, CustomaryLength distance, StrafeDirection strafeDirection) {
        drive = subsystem;
        intake = intakeSub;
        addRequirements(drive);
        this.distance = distance;
        this.strafeDirection = strafeDirection;
        //this.duration = duration*1000;

    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        drive.resetEncoders();
        intake.intake();
        //startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        drive.autoDrive(distance, strafeDirection);
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        drive.mecanumDrive(0, 0, 0);
        intake.stopIntake();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(drive.getDistance() < distance.get(CustomaryLengthUnit.FEET));
    }
}
