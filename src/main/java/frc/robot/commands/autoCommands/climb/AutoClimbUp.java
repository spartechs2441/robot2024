package frc.robot.commands.autoCommands.climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSub;

public class AutoClimbUp extends Command {
    private final ClimbSub climb;
    private final double duration;
    private double startTime;

    public AutoClimbUp(ClimbSub subsystem) {
        climb = subsystem;
        addRequirements(climb);
        duration = 900;
    }

    public AutoClimbUp(ClimbSub subsystem, double duration) {
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
        climb.climbUp();
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
