package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSub extends SubsystemBase {

    private final RelativeEncoder shooterEncoder;
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;
    private final CANSparkMax leftFeeder;
    private final CANSparkMax rightFeeder;


    public ShooterSub() {
        leftMotor = new CANSparkMax(Constants.Port.SHOOTER_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        rightMotor = new CANSparkMax(Constants.Port.SHOOTER_RIGHT, CANSparkLowLevel.MotorType.kBrushless);
        rightMotor.setInverted(true);
        leftMotor.setInverted(false);
        shooterEncoder = rightMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

        leftFeeder = new CANSparkMax(Constants.Port.FEEDER_LEFT, CANSparkLowLevel.MotorType.kBrushless);
        rightFeeder = new CANSparkMax(Constants.Port.FEEDER_RIGHT, CANSparkLowLevel.MotorType.kBrushless);

        this.resetEncoders();
    }

    /**
     * Resetting position for autonomous commands & Auto aiming during TeleOp
     */
    public void resetEncoders() {
        shooterEncoder.setPosition(0);
    }

    // TODO I actually have no idea if this will work, this will need testing, controls might be inverted

    /**
     * Revving flywheel in
     */
    public void in() {
        leftMotor.setVoltage(-0.1 * Constants.Speed.SHOOTER);
        rightMotor.setVoltage(-0.1 * Constants.Speed.SHOOTER);
    }

    /**
     * Revving flywheel out
     */
    public void out() {
        leftMotor.setVoltage(Constants.Speed.SHOOTER);
        rightMotor.setVoltage(Constants.Speed.SHOOTER);
    }

    /**
     * Stop motors from revving
     */
    public void stopShoot() {
        leftMotor.setVoltage(0);
        rightMotor.setVoltage(0);
    }
    public void feederIn() {
        leftFeeder.setVoltage(-Constants.Speed.FEEDER);
        rightFeeder.setVoltage(-Constants.Speed.FEEDER);
    }
    public void feederOut() {
        leftFeeder.setVoltage(Constants.Speed.FEEDER);
        rightFeeder.setVoltage(Constants.Speed.FEEDER);
    }
    public void stopFeeder() {
        leftFeeder.setVoltage(0);
        rightFeeder.setVoltage(0);
    }
}
