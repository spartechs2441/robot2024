package frc.robot.commands.teleop.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSub;

public class FeederInward extends Command {
    private final FeederSub feeder;

    public FeederInward(FeederSub subsystem) {
        feeder = subsystem;
        addRequirements(feeder);
    }

    @Override
    public void execute() {
        feeder.feederIn();
    }
}

