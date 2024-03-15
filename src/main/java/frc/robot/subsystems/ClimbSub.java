package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSub extends SubsystemBase {
    CANSparkMax leftMotor;
    CANSparkMax rightMotor;
    RelativeEncoder leftMotorEncoder;
    RelativeEncoder rightMotorEncoder;

    public ClimbSub() {
        leftMotor = new CANSparkMax(Constants.Port.LEFT_CLIMB, CANSparkLowLevel.MotorType.kBrushless);
        leftMotorEncoder = leftMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        leftMotor.setInverted(false);

        rightMotor = new CANSparkMax(Constants.Port.RIGHT_CLIMB, CANSparkLowLevel.MotorType.kBrushless);
        rightMotorEncoder = rightMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        rightMotor.setInverted(false);

        //Resetting position
        resetEncoders();

    }

    //Resetting position for autonomous commands & Auto aiming during TeleOp
    public void resetEncoders() {
        leftMotorEncoder.setPosition(0);
        rightMotorEncoder.setPosition(0);
    }

    public void climbUp() {
        leftMotor.setVoltage(Constants.Speed.CLIMB);
        rightMotor.setVoltage(Constants.Speed.CLIMB);
    }

    public void climbDown() {
        leftMotor.setVoltage(-Constants.Speed.CONVEYOR);
        rightMotor.setVoltage(-Constants.Speed.CONVEYOR);

    }

    public void climbStop() {
        leftMotor.setVoltage(0);
        rightMotor.setVoltage(0);
    }
}
