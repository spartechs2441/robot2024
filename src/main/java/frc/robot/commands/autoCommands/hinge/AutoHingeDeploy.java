package frc.robot.commands.autoCommands.hinge;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeHingeSub;
import frc.robot.subsystems.IntakeSub;

public class AutoHingeDeploy extends Command {
    private final IntakeHingeSub hinge;
    private double startTime;

    public AutoHingeDeploy(IntakeHingeSub subsystem) {
        hinge = subsystem;
        addRequirements(hinge);
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        hinge.resetEncoder();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        if(hinge.getDistance() < .7)
            hinge.deploy();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        hinge.stopDeploy();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(hinge.getDistance() < .7);
    }

}
