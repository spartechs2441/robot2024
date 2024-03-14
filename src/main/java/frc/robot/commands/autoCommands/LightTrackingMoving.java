package frc.robot.commands.autoCommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveTrainSub;

public class LightTrackingMoving extends Command {
    NetworkTable llight;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry tv;
    private double x;
    private double y;
    private double area;
    private Long isSomething;
    private DriveTrainSub driveSub;

    public LightTrackingMoving(DriveTrainSub subsystem) {
        llight = NetworkTableInstance.getDefault().getTable("limelight");
        ta = llight.getEntry("ta"); //area of reflective object
        tx = llight.getEntry("tx"); //displacement on x axis
        ty = llight.getEntry("ty"); //displacement on y axis
        tv = llight.getEntry("tv"); //0 or 1 depending on if there is a reflective object
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
        area = ta.getDouble(0.0);
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        isSomething = tv.getInteger(0);

        //CHECK IF SOMETHING'S HERE THEN TURN TOWARD IT
        if (isSomething == 1) {
            final double dist = 1f;
            final double backwardSpeed = (1 - Math.cbrt(area));
            final double forwardSpeed = (Math.sqrt(3) - Math.sqrt(area)) / 2;
            final double ySpeed = x * (1.0 / 40);
            final double rotationSpeed = x * (1.0 / 40);
            if (area < dist) {
                driveSub.mecanumDrive(forwardSpeed, 0, rotationSpeed);
            } else if (area >= dist) {
                driveSub.mecanumDrive(backwardSpeed, 0, ySpeed);
            }
        } else {
            driveSub.mecanumDrive(0, 0, 0);
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
