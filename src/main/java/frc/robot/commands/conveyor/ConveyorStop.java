package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSub;

public class ConveyorStop extends Command {
    private final ConveyorSub conveyor;

    public ConveyorStop(ConveyorSub subsystem) {
        conveyor = subsystem;
        addRequirements(conveyor);
    }

    @Override
    public void execute() {
        conveyor.stopConveyor();
    }

}
