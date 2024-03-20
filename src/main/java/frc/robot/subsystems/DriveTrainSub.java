package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.math.MathUtil;
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
    private double turningSpeed;
    private double speed;

    public DriveTrainSub() {

        this.speed = Constants.Speed.TELEOP;
        this.turningSpeed = Constants.Speed.TELEOP_ROTATION;

        CANSparkMax frontRight = new CANSparkMax(Constants.Port.FRONT_RIGHT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        frontRight.setInverted(false);
        CANSparkMax backRight = new CANSparkMax(Constants.Port.BACK_RIGHT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        backRight.setInverted(false);
        CANSparkMax frontLeft = new CANSparkMax(Constants.Port.FRONT_LEFT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        frontLeft.setInverted(true);
        CANSparkMax backLeft = new CANSparkMax(Constants.Port.BACK_LEFT_DRIVE, CANSparkLowLevel.MotorType.kBrushless);
        backLeft.setInverted(true);

        //initializing encoders
        frontRightEncoder = frontRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        frontRightEncoder.setPositionConversionFactor(1 / 1.7846595 / 3.78947369 / 1.0212766); //1.0169  HAVE TO REDO THIS

        backRightEncoder = backRight.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        backRightEncoder.setPositionConversionFactor(1 / 1.8252 / 3.78947369 / 1.0212766); //1.04   HAVE TO REDO THIS

        frontLeftEncoder = frontLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        frontLeftEncoder.setPositionConversionFactor(1 / 1.755 / 3.78947369 / 1.0212766); //1   HAVE TO REDO THIS

        backLeftEncoder = backLeft.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);
        backLeftEncoder.setPositionConversionFactor(1 / 1.70235 / 3.78947369 / 1.0212766); //.97   HAVE TO REDO THIS

        //initializing the mecanum drive
        mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
        mecanumDrive.setDeadband(0.2);

        //resetting encoders
        resetEncoders();
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
     *
     * @param length          distance to drive
     * @param strafeDirection direction to drive
     * @apiNote Gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
     */
    public void autoDrive(final CustomaryLength length, final StrafeDirection strafeDirection) {
        final double feet = length.get(CustomaryLengthUnit.FEET);
        double brake;

        while (getDistance() < feet) {
            brake = 1 - getDistance() / feet;
            switch (strafeDirection) {
                case FORWARD -> mecanumDrive.driveCartesian(this.speed * brake, 0, 0);
                case LEFT -> mecanumDrive.driveCartesian(0, this.speed * brake, 0);
                case RIGHT -> mecanumDrive.driveCartesian(0, -this.speed * brake, 0);
                case BACKWARDS -> mecanumDrive.driveCartesian(-this.speed * brake, 0, 0);
            }
        }
    }

    /**
     * Wrapper method for driveCartesian
     * Applies multiplier and deadband so be careful when using in autonomous
     * For documentation, go to see also
     *
     * @see MecanumDrive#driveCartesian(double, double, double)
     */
    public void mecanumDrive(double xSpeed, double ySpeed, double zRotation) {
        double m = 1;
        double a = ((.2-Math.exp(m))/(Math.exp(m)-1));
        double k = Math.log((1+a)/a);
        xSpeed = a*(Math.exp(k*(xSpeed*this.speed))-1); //might be an exponential change
//        xSpeed *= this.speed;
        ySpeed *= -this.speed;
        zRotation = -MathUtil.applyDeadband(zRotation * this.turningSpeed, 0.2);

        mecanumDrive.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTurningSpeed() {
        return turningSpeed;
    }

    public void setTurningSpeed(double turningSpeed) {
        this.turningSpeed = turningSpeed;
    }
}
