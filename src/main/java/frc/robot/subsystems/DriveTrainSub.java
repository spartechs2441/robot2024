package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;
import frc.robot.utils.StrafeDirection;

public class DriveTrainSub extends SubsystemBase {

    //encoders for drive
    private final RelativeEncoder frontRightEncoder;
    private final RelativeEncoder frontLeftEncoder;
    private final RelativeEncoder backRightEncoder;
    private final RelativeEncoder backLeftEncoder;
    private final MecanumDrive mecanumDrive;

    public DriveTrainSub() {

        CANSparkMax frontRight = new CANSparkMax(Constants.Port.FRONT_RIGHT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        frontRight.setInverted(true);
        CANSparkMax backRight = new CANSparkMax(Constants.Port.BACK_RIGHT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        backRight.setInverted(true);
        CANSparkMax frontLeft = new CANSparkMax(Constants.Port.FRONT_LEFT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        CANSparkMax backLeft = new CANSparkMax(Constants.Port.BACK_LEFT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);

        //initializing encoders
        frontRightEncoder = frontRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        frontRightEncoder.setPositionConversionFactor(1 / 1.7846595 / 3.78947369 / 1.0212766); //1.0169

        backRightEncoder = backRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        backRightEncoder.setPositionConversionFactor(1 / 1.8252 / 3.78947369 / 1.0212766); //1.04

        frontLeftEncoder = frontLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        frontLeftEncoder.setPositionConversionFactor(1 / 1.755 / 3.78947369 / 1.0212766); //1

        backLeftEncoder = backLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        backLeftEncoder.setPositionConversionFactor(1 / 1.70235 / 3.78947369 / 1.0212766); //.97

        //initializing the mecanum drive
        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecanumDrive.setDeadband(0.3);

        //resetting encoders
        frontRightEncoder.setPosition(0);
        frontLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
    }

    /**
     * Resets the encoders to zero
     */
    public void resetEncoders() {
        frontRightEncoder.setPosition(0);
        frontLeftEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
    }

    /**
     * Gets the distance of how far the robot has traveled
     */
    public double getDistance() {
        return Math.abs(
                (
                        backRightEncoder.getPosition() +
                                backLeftEncoder.getPosition() +
                                frontRightEncoder.getPosition() +
                                frontLeftEncoder.getPosition()
                ) / 4
        );
    }

    /**
     * Drives a certain distance
     * Should be used in autonomous for precise movement
     * @param length distance to drive
     * @param strafeDirection direction to drive
     * @apiNote Gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
     */
    public void autoDrive(CustomaryLength length, StrafeDirection strafeDirection) {
        double feet = length.get(CustomaryLengthUnit.FEET);
        double brake = 1 - getDistance() / feet;

        switch (strafeDirection) {
            case FORWARD -> {
                while (getDistance() < feet) {
                    mecanumDrive(Constants.Speed.AUTO * brake, 0, 0);
                }
            }
            case LEFT -> {
                while (getDistance() < feet) {
                    mecanumDrive(0, Constants.Speed.AUTO * brake, 0);
                }
            }
            case RIGHT -> {
                while (getDistance() < feet) {
                    mecanumDrive(0, -Constants.Speed.AUTO * brake, 0);
                }
            }
            case BACKWARDS -> {
                while (getDistance() < feet) {
                    mecanumDrive(-Constants.Speed.AUTO * brake, 0, 0);
                }
            }
        }
    }

    /**
     * Wrapper method for driveCartesian
     * For documentation, go to see also
     * @see MecanumDrive#driveCartesian(double, double, double)
     */
    public void mecanumDrive(double xSpeed, double ySpeed, double zRotation) {
        xSpeed *= Constants.Speed.TELEOP;
        ySpeed *= Constants.Speed.TELEOP;
        zRotation *= Constants.Speed.TELEOP_ROTATION;

        if (zRotation > .25)
            mecanumDrive.driveCartesian(xSpeed, ySpeed, (zRotation - .25) / 1.3);
        else if (zRotation < -.25)
            mecanumDrive.driveCartesian(xSpeed, ySpeed, (zRotation + .25) / 1.3);
        else {
            mecanumDrive.driveCartesian(xSpeed, ySpeed, 0);
        }
    }
}
