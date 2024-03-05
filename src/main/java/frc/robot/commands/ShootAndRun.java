// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoCommands.AutoDrive;
import frc.robot.commands.autoCommands.flyWheel.AutoFlywheelsOutward;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;
import frc.robot.utils.StrafeDirection;


public final class ShootAndRun extends SequentialCommandGroup {
    public ShootAndRun(ShooterSub shooterSub, DriveTrainSub driveTrainSub) {
        addCommands(
                new AutoFlywheelsOutward(shooterSub),
                new AutoDrive(driveTrainSub, new CustomaryLength(10, CustomaryLengthUnit.FEET), StrafeDirection.BACKWARDS)
        );

    }
}
