package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSub;

public class ConveyorForward extends Command {
    private final ConveyorSub conveyor;

    public ConveyorForward(ConveyorSub subsystem) {
        conveyor = subsystem;
        addRequirements(conveyor);
    }

    @Override
    public void execute() {
        conveyor.conveyorUp();
    }

}
