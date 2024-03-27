package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FeederSub extends SubsystemBase {
    private final CANSparkMax feederMotor;


    public FeederSub() {
        feederMotor = new CANSparkMax(Constants.Port.FEEDER, CANSparkLowLevel.MotorType.kBrushless);
    }
    public void feederIn() {
        feederMotor.setVoltage(Constants.Speed.FEEDER);
    }
    public void feederOut() {
        feederMotor.setVoltage(-Constants.Speed.FEEDER);
    }
    public void stopFeeder() {
        feederMotor.setVoltage(0);
    }
}
