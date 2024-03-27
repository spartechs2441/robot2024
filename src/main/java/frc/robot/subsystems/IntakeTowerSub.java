package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Represents the ground intake of the robot which consists of an intake and deployment hinge
 */
public class IntakeTowerSub extends SubsystemBase {

    private final CANSparkMax intakeTowerMotor;

    public IntakeTowerSub() {
        intakeTowerMotor = new CANSparkMax(Constants.Port.INTAKE_TOWER, CANSparkLowLevel.MotorType.kBrushless);
        intakeTowerMotor.setInverted(true);
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
