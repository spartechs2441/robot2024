package frc.robot.commands.teleop.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeHingeSub;

// TODO Add limit switch
public class TeleopDeploy extends Command {
    private final IntakeHingeSub hinge;

    public TeleopDeploy(IntakeHingeSub hinge) {
        this.hinge = hinge;
        addRequirements(hinge);
    }

    @Override
    public void execute() {
        hinge.deploy();
    }
}
