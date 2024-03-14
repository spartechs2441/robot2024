package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSub;

public class AutoShoot extends Command {
    private final ShooterSub shooter;
    private final double duration;
    private double startTime;

    public AutoShoot(ShooterSub subsystem) {
        shooter = subsystem;
        addRequirements(shooter);
        duration = 3000; //Will change
    }

    // only goes once at beginning when command is called
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    // keeps repeating until the command ends
    @Override
    public void execute() {
        shooter.out();
        if ((System.currentTimeMillis()-startTime) <= 2000){
            shooter.feederOut();
        }

    }

    //only goes once at end when command is finishing
    @Override
    public void end(boolean interrupted) {
        shooter.stopShoot();
        shooter.stopFeeder();
    }

    //condition for the command to end on its own
    @Override
    public boolean isFinished() {
        return !(System.currentTimeMillis() - startTime < duration);
    }

}
