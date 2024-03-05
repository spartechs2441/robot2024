package frc.robot.commands.teleop.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FeederOutward extends Command {
    private final ShooterSub shooter;

    public FeederOutward(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.feederOut();
    }
}

