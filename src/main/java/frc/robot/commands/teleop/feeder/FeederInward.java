package frc.robot.commands.teleop.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FeederInward extends Command {
    private final ShooterSub shooter;

    public FeederInward(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.feederIn();
    }
}

