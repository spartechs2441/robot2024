package frc.robot.commands.autoCommands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrainSub;

import java.util.Arrays;

public class AprilTagTrackingMoving extends Command {
    NetworkTable light;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private NetworkTableEntry id;
    private NetworkTableEntry tyaw;
    private DriveTrainSub driveSub;
    private boolean done;


    public AprilTagTrackingMoving(DriveTrainSub subsystem) {
        // Refer to https://docs.limelightvision.io/docs/docs-limelight/apis/complete-networktables-api
        light = NetworkTableInstance.getDefault().getTable("limelight");
        ta = light.getEntry("ta"); //area of reflective object
        tx = light.getEntry("tx"); //displacement on x axis
        ty = light.getEntry("ty"); //displacement on y axis
        tv = light.getEntry("tv"); //0 or 1 depending on if there is a reflective object
        id = light.getEntry("tid");
        // TODO REPLACE THIS
        tyaw = light.getEntry("targetpose_robotspace");
        driveSub = subsystem;
        addRequirements(driveSub);
        done = false;
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
        double area = ta.getDouble(0.0);
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);

        // check if something's here and checks if that something is the right id then move toward it
        // 5 & 6 are the ids for the AMP
        if (!isTargeting || (5 != tagId && 6 != tagId)) {
            driveSub.stopDrive();
            done = true;
        }

        // throw new Exception("If you do not know why this is here, READ MY DANG COMMIT -Alex");
        double yaw = botpose[4];
        // 45 is a completely arbitrary number
        final double rotationSpeed = Math.min(yaw / 45, 1.0);
        if (yaw > 5) {
            driveSub.mecanumDrive(0, 0, -rotationSpeed);
        } else if (yaw < -5) {
            driveSub.mecanumDrive(0, 0, rotationSpeed);
        }

        final double dist = 0.5f;
        final double backwardSpeed = (1 - Math.cbrt(area));
        final double forwardSpeed = (Math.sqrt(3) - Math.sqrt(area)) / 2;
        final double sidewaysSpeed = x * (1.0 / 40);
        if (yaw > -5 && yaw < 5){
            if (area < dist) {
                driveSub.mecanumDrive(-forwardSpeed, sidewaysSpeed, 0);
            } else if (area > dist) {
                driveSub.mecanumDrive(-backwardSpeed, sidewaysSpeed, 0);
            }
            else{
                done = true;
            }
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
        return done;
    }
}