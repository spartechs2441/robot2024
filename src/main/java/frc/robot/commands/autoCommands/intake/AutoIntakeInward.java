package frc.robot.commands.autoCommands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

public class AutoIntakeInward extends Command {
    private final IntakeSub intake;
    private final double duration;
    private double startTime;

    public AutoIntakeInward(IntakeSub subsystem) {
        intake = subsystem;
        addRequirements(intake);
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
        intake.intake();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
