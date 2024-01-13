package frc.robot.commands.teleop.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

public class TeleopStopDeploy extends Command {
    private final IntakeSub intake;

    public TeleopStopDeploy(IntakeSub intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.stopDeploy();
    }

}
