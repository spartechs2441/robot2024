package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSub;

public class ConveyorUp extends Command {
    private final ConveyorSub conveyor;

    public ConveyorUp(ConveyorSub subsystem) {
        conveyor = subsystem;
        addRequirements(conveyor);
    }

    @Override
    public void execute() {
        conveyor.conveyorUp();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
