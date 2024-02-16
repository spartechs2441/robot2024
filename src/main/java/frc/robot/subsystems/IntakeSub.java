package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Represents the ground intake of the robot which consists of an intake and deployment hinge
 */
public class IntakeSub extends SubsystemBase {

    private final CANSparkMax intakeMotor;
    private final CANSparkMax deployMotor;
    private final CANSparkMax intakeTowerMotor;

    public IntakeSub() {
        intakeMotor = new CANSparkMax(Constants.Port.INTAKE, CANSparkLowLevel.MotorType.kBrushless);
        deployMotor = new CANSparkMax(Constants.Port.INTAKE_HINGE, CANSparkLowLevel.MotorType.kBrushless);
        intakeTowerMotor = new CANSparkMax(Constants.Port.INTAKE_TOWER, CANSparkLowLevel.MotorType.kBrushless);
        intakeTowerMotor.setInverted(true);
    }

    /**
     * Deploys the intake
     */
    public void deploy() {
        deployMotor.setVoltage(-Constants.Voltage.DEPLOY);
    }

    /**
     * Puts up the intake
     */
    public void retract() {
        deployMotor.setVoltage(Constants.Voltage.DEPLOY);
    }

    public void stopDeploy() {
        deployMotor.setVoltage(0);
    }

    /**
     * Sucks in a note
     */
    public void intake() {
        intakeMotor.setVoltage(Constants.Voltage.INTAKE);
    }

    /**
     * Spits out a note
     */
    public void eject() {
        intakeMotor.setVoltage(-Constants.Voltage.INTAKE);
    }

    public void stopIntake() {
        intakeMotor.setVoltage(0);
    }

    public void intakeTowerRise() {
        intakeTowerMotor.setVoltage(Constants.Voltage.INTAKE_TOWER);
    }

    public void intakeTowerDrop() {
        intakeTowerMotor.setVoltage(-Constants.Voltage.INTAKE_TOWER);
    }

    public void intakeTowerStop() {
        intakeTowerMotor.setVoltage(0);
    }
}
