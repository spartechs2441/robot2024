package frc.robot.commands.teleop.climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class ClimbDown extends Command {
    private final ClimbSub climb;

    public ClimbDown(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.climbDown();
    }
}
