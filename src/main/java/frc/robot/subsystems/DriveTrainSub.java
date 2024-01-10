 package frc.robot.subsystems;

 import com.revrobotics.CANSparkMax;
 import com.revrobotics.CANSparkMaxLowLevel.MotorType;
 import com.revrobotics.RelativeEncoder;
 import com.revrobotics.SparkMaxRelativeEncoder.Type;
 import edu.wpi.first.wpilibj.drive.MecanumDrive;
 import edu.wpi.first.wpilibj2.command.SubsystemBase;
 import frc.robot.Constants;
 import frc.robot.StrafeDirection;
 import frc.robot.shuffleboard.CANSparkMaxMotorManager;
 import frc.robot.utils.CustomaryLength;
 import frc.robot.utils.CustomaryLengthUnit;

 public class DriveTrainSub extends SubsystemBase {

     private final CANSparkMax frontRight;
     private final CANSparkMax backRight;
     private final CANSparkMax frontLeft;
     private final CANSparkMax backLeft;

     //encoders for drive
     private final RelativeEncoder frontRightEncoder;
     private final RelativeEncoder frontLeftEncoder;
     private final RelativeEncoder backRightEncoder;
     private final RelativeEncoder backLeftEncoder;
     private final MecanumDrive mecanumDrive;

     public DriveTrainSub() {

         CANSparkMaxMotorManager motorManager = CANSparkMaxMotorManager.getInstance();

         frontRight = motorManager.retrieveMotor(Constants.frontRightDrive, MotorType.kBrushless);
         frontRight.setInverted(true);

         backRight = motorManager.retrieveMotor(Constants.backRightDrive, MotorType.kBrushless);
         backRight.setInverted(true);

         frontLeft = motorManager.retrieveMotor(Constants.frontLeftDrive, MotorType.kBrushless);
         backLeft = motorManager.retrieveMotor(Constants.backLeftDrive, MotorType.kBrushless);

         // frontRight = new CANSparkMax(Constants.frontRightDrive, MotorType.kBrushless);
         // frontRight.setInverted(true);
         // backRight = new CANSparkMax(Constants.backRightDrive, MotorType.kBrushless);
         // backRight.setInverted(true);
         // frontLeft = new CANSparkMax(Constants.frontLeftDrive, MotorType.kBrushless);
         // backLeft = new CANSparkMax(Constants.backLeftDrive, MotorType.kBrushless);

         //initializing encoders
         frontRightEncoder = frontRight.getEncoder(Type.kHallSensor, 42);
         frontRightEncoder.setPositionConversionFactor(1/1.7846595/3.78947369/1.0212766); //1.0169

         backRightEncoder = backRight.getEncoder(Type.kHallSensor, 42);
         backRightEncoder.setPositionConversionFactor(1/1.8252/3.78947369/1.0212766); //1.04

         frontLeftEncoder = frontLeft.getEncoder(Type.kHallSensor, 42);
         frontLeftEncoder.setPositionConversionFactor(1/1.755/3.78947369/1.0212766); //1

         backLeftEncoder = backLeft.getEncoder(Type.kHallSensor, 42);
         backLeftEncoder.setPositionConversionFactor(1/1.70235/3.78947369/1.0212766); //.97

         //initializing the mecanum drive
         mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
         mecanumDrive.setDeadband(0.3);

         //resetting encoders
         frontRightEncoder.setPosition(0);
         frontLeftEncoder.setPosition(0);
         backRightEncoder.setPosition(0);
         backLeftEncoder.setPosition(0);
     }

     //resets the encoders to 0
     public void resetEncoder() {
         frontRightEncoder.setPosition(0);
         frontLeftEncoder.setPosition(0);
         backRightEncoder.setPosition(0);
         backLeftEncoder.setPosition(0);
     }

     //tells the distance of how far you've traveled
     public double getDistance(){
         return Math.abs(
                 (
                         backRightEncoder.getPosition() +
                         backLeftEncoder.getPosition() +
                         frontRightEncoder.getPosition() +
                         frontLeftEncoder.getPosition()
                 ) / 4
         );
     }

     //command for autodrive
     //also gradually increases break as you get closer to your destination so the robot stops exactly where you need it do
     public void autoDrive(CustomaryLength length, StrafeDirection strafeDirection) {
         double feet = length.get(CustomaryLengthUnit.FEET);
         double brake = 1 - getDistance() / feet;

         switch (strafeDirection) {
             case FORWARD: {
                 while (getDistance() < feet) {
                     turboMecanumDrive(-Constants.autoDrive * brake, 0, 0);
                 }
                 break;
             }
             case LEFT: {
                 while (getDistance() < feet) {
                     turboMecanumDrive(0, -Constants.autoDrive * brake, 0);
                 }
                 break;
             }

             case RIGHT: {
                 while (getDistance() < feet) {
                     turboMecanumDrive(0, Constants.autoDrive * brake, 0);
                 }
                 break;
             }

             case BACKWARDS: {
                 while (getDistance() < feet) {
                     turboMecanumDrive(Constants.autoDrive * brake, 0, 0);
                 }
                 break;
             }

         }
     }

     public void turboMecanumDrive(double ySpeed, double xSpeed, double zRotation) {
         if(zRotation > .25)
             mecanumDrive.driveCartesian(-ySpeed / 1.4, xSpeed, (zRotation-.25)/1.3);
         else if(zRotation < -.25)
             mecanumDrive.driveCartesian(-ySpeed / 1.4, xSpeed, (zRotation+.25)/1.3);
         else{
             mecanumDrive.driveCartesian(-ySpeed / 1.4, xSpeed, 0);
         }
     }
     public void mecanumDrive(double ySpeed, double xSpeed, double zRotation) {
         if(zRotation > .25)
             mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, (zRotation-.25)/2);
         else if(zRotation < -.25)
             mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, (zRotation+.25)/2);
         else
             mecanumDrive.driveCartesian(-ySpeed / 2, xSpeed/2, 0);
     }
 }
