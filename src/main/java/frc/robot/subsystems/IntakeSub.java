package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Represents the ground intake of the robot which consists of an intake and deployment hinge
 */
public class IntakeSub extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private final CANSparkMax deployMotor;
    public IntakeSub() {
        intakeMotor = new CANSparkMax(Constants.Port.INTAKE, CANSparkLowLevel.MotorType.kBrushless);
        deployMotor = new CANSparkMax(Constants.Port.INTAKE_HINGE, CANSparkLowLevel.MotorType.kBrushless);
    }

    /**
     * Deploys the intake
     */
    public void deploy() {
        deployMotor.setVoltage(Constants.Voltage.DEPLOY);
    }
    /**
     * Puts up the intake
     */
    public void retract() {
        deployMotor.setVoltage(-Constants.Voltage.DEPLOY);
    }

    public void stopDeploy() {
        deployMotor.setVoltage(0);
    }

    /**
     * Sucks in a note
     */
    public void intake() {
        deployMotor.setVoltage(Constants.Voltage.DEPLOY);
    }

    /**
     * Spits out a note
     */
    public void eject() {
        deployMotor.setVoltage(-Constants.Voltage.DEPLOY);
    }

    public void stopIntake() {
        deployMotor.setVoltage(0);
    }

}
