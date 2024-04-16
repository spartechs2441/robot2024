// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.ShooterSub;
import frc.robot.utils.CustomaryLength;
import frc.robot.utils.CustomaryLengthUnit;
import frc.robot.utils.StrafeDirection;


public final class JustRun extends SequentialCommandGroup {
    public JustRun(DriveTrainSub driveTrainSub) {
        addCommands(
                new AutoDrive(driveTrainSub, new CustomaryLength(10, CustomaryLengthUnit.FEET), StrafeDirection.FORWARD)
        );

    }
}
