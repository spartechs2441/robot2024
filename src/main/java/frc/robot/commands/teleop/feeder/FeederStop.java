package frc.robot.commands.teleop.feeder;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FeederStop extends Command {
    private final ShooterSub shooter;

    public FeederStop(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.stopFeeder();
    }
}

