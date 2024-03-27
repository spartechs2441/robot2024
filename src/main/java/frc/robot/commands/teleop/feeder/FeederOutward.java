package frc.robot.commands.teleop.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSub;

public class FeederOutward extends Command {
    private final FeederSub feeder;

    public FeederOutward(FeederSub subsystem) {
        feeder = subsystem;
        addRequirements(feeder);
    }

    @Override
    public void execute() {
        feeder.feederOut();
    }
}

