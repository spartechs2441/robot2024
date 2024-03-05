package frc.robot.commands.teleop.flywheel;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class FlywheelsInward extends Command {
    private final ShooterSub shooter;

    public FlywheelsInward(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.in();
    }
}

