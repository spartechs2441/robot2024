package frc.robot.commands.autoCommands.climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class AutoClimbDown extends Command {
    private final ClimbSub climb;
    private final double duration;
    private double startTime;

    public AutoClimbDown(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
        duration = 900;
    }

    public AutoClimbDown(ClimbSub subsystem, double duration) {
        climb = subsystem;
        addRequirements(climb);
        this.duration = duration;
    }
    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        climb.climbDown();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        climb.climbStop();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
