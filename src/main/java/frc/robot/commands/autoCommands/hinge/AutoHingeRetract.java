package frc.robot.commands.autoCommands.hinge;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

public class AutoHingeRetract extends Command {
    private final IntakeSub hinge;
    private final double duration;
    private double startTime;

    public AutoHingeRetract(IntakeSub subsystem) {
        hinge = subsystem;
        addRequirements(hinge);
        duration = 900; //Will change
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        hinge.retract();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        hinge.stopDeploy();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
