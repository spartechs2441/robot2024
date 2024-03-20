package frc.robot.commands.teleop.climb.leftClimb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class ClimbStopLeft extends Command {
    private final ClimbSub climb;

    public ClimbStopLeft(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
    }

    @Override
    public void execute() {
        climb.climbStopLeft();
    }
}