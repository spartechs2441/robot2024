package frc.robot.commands.autoCommands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrainSub;

public class LightTrackingMoving extends Command {
    NetworkTable light;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private NetworkTableEntry id;
    private NetworkTableEntry tyaw;
    private DriveTrainSub driveSub;
    private Rotation2d test;

    public LightTrackingMoving(DriveTrainSub subsystem) {
        // Refer to https://docs.limelightvision.io/docs/docs-limelight/apis/complete-networktables-api
        light = NetworkTableInstance.getDefault().getTable("limelight");
        ta = light.getEntry("ta"); //area of reflective object
        tx = light.getEntry("tx"); //displacement on x axis
        ty = light.getEntry("ty"); //displacement on y axis
        tv = light.getEntry("tv"); //0 or 1 depending on if there is a reflective object
        id = light.getEntry("tid");
        // TODO REPLACE THIS
        tyaw = light.getEntry("botpose");
        driveSub = subsystem;
        addRequirements(driveSub);
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        boolean isTargeting = tv.getInteger(0) == 1;
        double tagId = id.getDouble(0.0);
        double[] botpose = tyaw.getDoubleArray(new double[] {});

        // check if something's here and checks if that something is the right id then move toward it
        // 5 & 6 are the ids for the AMP
        if (isTargeting && 5 != tagId && 6 != tagId) {
            driveSub.stopDrive();
            return;
        }

        // throw new Exception("If you do not know why this is here, READ MY DANG COMMIT -Alex");
        double yaw = botpose[2];

        // 45 is a completely arbitrary number
        final double rotationSpeed = Math.min(yaw / 45, 1.0);
        if (yaw > 10) {
            driveSub.mecanumDrive(0, 0, rotationSpeed);
        } else if (yaw < -10) {
            driveSub.mecanumDrive(0, 0, -rotationSpeed);
        } else {
            driveSub.stopDrive();
        }
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        CommandScheduler.getInstance().cancelAll();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}