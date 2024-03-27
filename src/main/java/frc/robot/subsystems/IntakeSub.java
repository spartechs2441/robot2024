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

    public IntakeSub() {
        intakeMotor = new CANSparkMax(Constants.Port.INTAKE, CANSparkLowLevel.MotorType.kBrushless);
    }

    /**
     * Sucks in a note
     */
    public void intake() {
        intakeMotor.setVoltage(-Constants.Voltage.INTAKE);
    }

    /**
     * Spits out a note
     */
    public void eject() {
        intakeMotor.setVoltage(Constants.Voltage.INTAKE);
    }

    public void stopIntake() {
        intakeMotor.setVoltage(0);
    }
}
