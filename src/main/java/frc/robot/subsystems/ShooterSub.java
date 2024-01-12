package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSub extends SubsystemBase {

    RelativeEncoder shooterEncoder;
    CANSparkMax shooterMotor;

    public ShooterSub(){
        shooterMotor = new CANSparkMax(Constants.Port.SHOOTER, CANSparkLowLevel.MotorType.kBrushless);

        shooterEncoder = shooterMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

        //Resetting positiion
        shooterEncoder.setPosition(0);

    }

    //Resetting position for autonomous commands & Auto aiming during TeleOp
    public void resetEncoders(){
        shooterEncoder.setPosition(0);
    }

    //Revving flywheel outwards
    public void flywheelOut(){
        shooterMotor.setVoltage(Constants.Speed.SHOOTER);
    }
    //
    public void stopShoot(){
        shooterMotor.setVoltage(0);
    }
    public void load(){
        shooterMotor.setVoltage(-1);
    }
}
