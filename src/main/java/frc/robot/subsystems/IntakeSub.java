package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSub extends SubsystemBase {

    CANSparkMax intakeMotor;
    CANSparkMax deployMotor;
    public IntakeSub() {
        intakeMotor = new CANSparkMax(Constants.Port.INTAKE, CANSparkLowLevel.MotorType.kBrushless);
        deployMotor = new CANSparkMax(Constants.Port.INTAKE_HINGE, CANSparkLowLevel.MotorType.kBrushless);
    }
}
