package frc.robot.commands.teleop.climb.rightClimb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class ClimbDownRight extends Command {
    private final ClimbSub climb;

    public ClimbDownRight(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.climbDownRight();
    }
}
