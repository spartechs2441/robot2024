package frc.robot.commands.teleop.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeHingeSub;

public class TeleopStopDeploy extends Command {
    private final IntakeHingeSub hinge;

    public TeleopStopDeploy(IntakeHingeSub hinge) {
        this.hinge = hinge;
        addRequirements(hinge);
    }

    @Override
    public void execute() {
        hinge.stopDeploy();
    }

}
