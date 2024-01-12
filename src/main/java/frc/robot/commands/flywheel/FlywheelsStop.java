package frc.robot.commands.flywheel;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FlywheelsStop extends Command {
    private final ShooterSub shooter;

    public FlywheelsStop(ArmSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.stopShoot();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
