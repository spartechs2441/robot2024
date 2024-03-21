package frc.robot.commands.autoCommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;

public class DumpLogs extends Command {

    private final NetworkTable light;
    private final NetworkTableEntry json;

    public DumpLogs() {
        light = NetworkTableInstance.getDefault().getTable("limelight");
        // If the JSON data doesn't contain AprilTag and 3D Data (https://docs.limelightvision.io/docs/docs-limelight/apis/complete-networktables-api#apriltag-and-3d-data)
        // Try get all the fields that have yaw on it
        json = light.getEntry("json");

    }
    @Override
    public void initialize() {
        String json = this.json.getString("JSON FAILED TO LOAD");
        System.out.println("JSON Data: " + json);
    }
}
