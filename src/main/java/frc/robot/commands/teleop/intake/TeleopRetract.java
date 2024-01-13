package frc.robot.commands.teleop.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

// TODO Add limit switch
public class TeleopRetract extends Command {
    private final IntakeSub intake;

    public TeleopRetract(IntakeSub intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.retract();
    }

}
