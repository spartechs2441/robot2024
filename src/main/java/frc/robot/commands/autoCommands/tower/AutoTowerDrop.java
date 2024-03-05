package frc.robot.commands.autoCommands.tower;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

public class AutoTowerDrop extends Command {
    private final IntakeSub tower;
    private final double duration;
    private double startTime;

    public AutoTowerDrop(IntakeSub subsystem) {
        tower = subsystem;
        addRequirements(tower);
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
        tower.intakeTowerDrop();
    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        tower.intakeTowerStop();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
