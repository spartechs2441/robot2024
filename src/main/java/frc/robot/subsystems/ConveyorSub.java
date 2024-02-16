package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkRelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSub extends SubsystemBase {
    CANSparkMax conveyorMotor;
    RelativeEncoder conveyorEncoder;

    public ConveyorSub() {
        conveyorMotor = new CANSparkMax(Constants.Port.CONVEYOR, CANSparkLowLevel.MotorType.kBrushless);

        conveyorEncoder = conveyorMotor.getEncoder(SparkRelativeEncoder.Type.kHallSensor, 42);

        //Resetting position
        conveyorEncoder.setPosition(0);

    }

    //Resetting position for autonomous commands & Auto aiming during TeleOp
    public void resetEncoders() {
        conveyorEncoder.setPosition(0);
    }

    public void conveyorDown() {
        conveyorMotor.setVoltage(-Constants.Speed.CONVEYOR);
    }

    public void conveyorUp() {
        conveyorMotor.setVoltage(Constants.Speed.CONVEYOR);
    }

    public void stopConveyor() {
        conveyorMotor.setVoltage(0);
    }
}
