package frc.robot.commands.autoCommands.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class AutoFeederOutward extends Command {
    private final ShooterSub feeder;
    private final double duration;
    private double startTime;

    public AutoFeederOutward(ShooterSub subsystem) {
        feeder = subsystem;
        addRequirements(feeder);
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
        feeder.feederOut();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        feeder.stopFeeder();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
