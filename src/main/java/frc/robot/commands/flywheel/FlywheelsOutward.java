package frc.robot.commands.flywheel;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FlywheelsOutward extends Command {
    private final ShooterSub shooter;

    public FlywheelsOutward(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.flywheelOut();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
