package frc.robot.commands.autoCommands.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSub;
import frc.robot.subsystems.ShooterSub;

public class AutoFeederInward extends Command {
    private final FeederSub feeder;
    private final double duration;
    private double startTime;

    public AutoFeederInward(FeederSub subsystem) {
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
        feeder.feederIn();
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
