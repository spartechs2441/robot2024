package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class AutoShoot extends Command {
    private final ShooterSub flywheels;
    private final double duration;
    private double startTime;

    public AutoShoot(ShooterSub subsystem) {
        flywheels = subsystem;
        addRequirements(flywheels);
        duration = 900; //Will change
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        flywheels.out();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        flywheels.stopShoot();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
