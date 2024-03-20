package frc.robot.commands.teleop.climb.rightClimb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class ClimbStopRight extends Command {
    private final ClimbSub climb;

    public ClimbStopRight(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.climbStopRight();
    }
}