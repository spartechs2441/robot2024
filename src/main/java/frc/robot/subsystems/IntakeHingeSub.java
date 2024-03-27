package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Represents the ground intake of the robot which consists of an intake and deployment hinge
 */
public class IntakeHingeSub extends SubsystemBase {
    private final CANSparkMax hingeMotor;

    public IntakeHingeSub() {

        hingeMotor = new CANSparkMax(Constants.Port.INTAKE_HINGE, CANSparkLowLevel.MotorType.kBrushless);
    }

    /**
     * Deploys the intake
     */
    public void deploy() {
        hingeMotor.setVoltage(Constants.Voltage.HINGE);
    }

    /**
     * Puts up the intake
     */
    public void retract() {
        hingeMotor.setVoltage(-Constants.Voltage.HINGE);
    }

    public void stopDeploy() {
        hingeMotor.setVoltage(0);
    }
}
