package frc.robot.commands.autoCommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class StopCommands extends Command {
    private boolean isFinished = false;


    public StopCommands() {}
    @Override
    public void initialize() {
        CommandScheduler.getInstance().cancelAll();
        isFinished = true;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
