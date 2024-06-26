// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * Shows the CAN IDs of all the motors
     */
    public static class Port {
        public static final int MAIN_JOYSTICK = 0;
        public static final int SECONDARY_JOYSTICK = 1;
        public static final int BACK_LEFT_DRIVE = 5;
        public static final int FRONT_LEFT_DRIVE = 4;
        public static final int FRONT_RIGHT_DRIVE = 1;
        public static final int BACK_RIGHT_DRIVE = 2;

        // robot has not been built so change these later
        public static final int INTAKE = 12;
        public static final int INTAKE_HINGE = 10;
        public static final int INTAKE_TOWER = 13;
        public static final int SHOOTER_RIGHT = 11;
        public static final int SHOOTER_LEFT = 6;
        public static final int FEEDER = 7;
        public static final int LEFT_CLIMB = 9;
        public static final int RIGHT_CLIMB = 3;
    }

    /**
     * Multiplies the values when driving
     * To avoid runtime errors, do not set these fields above 1
     */
    public static class Speed {
        public static final double AUTO = .6;
        public static final double TELEOP = 1;
        public static final double TELEOP_ROTATION = .5;
        public static final double SHOOTER = 11;
        public static final double FEEDER = 4;
        public static final double CLIMB = 10;

   }

    /**
     * Contains voltages of all the motors,
     */
    public static class Voltage {
        // TODO Change these values
        public static final double HINGE = 3;
        public static final double INTAKE = 4;
        public static final double INTAKE_TOWER = 8;

    }

    public static class RightButtons {
        /**
         * Button for sucking a note in
         */
        public static final int INTAKE = 7;
        /**
         * Button for spitting a note out
         */
        public static final int EJECT = 8;
        /**
         * Button for deploying the intake
         */
        public static final int DEPLOY = 12;
        /**
         * Button for putting up the intake
         */
        public static final int RETRACT = 11;
        /**
         * Button for Raising the intake tower
         */
        public static final int RISE = 9;
        /**
         * Button for Dropping the intake tower
         */
        public static final int DROP = 10;
        /**
         * Button for moving robot about the Amp
         */
        public static final int LIMELIGHT = 3;

    }
    public static class LeftButtons {
        /**
         * Button for Raising the Left Climb
         */
        public static final int LEFT_UPPIES = 10;
        /**
         * Button for Lowering the Left Climb
         */
        public static final int LEFT_DOWNS = 9;
        /**
         * Button for Raising the Right Climb
         */
        public static final int RIGHT_UPPIES = 12;
        /**
         * Button for Lowering the Right Climb
         */
        public static final int RIGHT_DOWNS = 11;
        /**
         * Button for Raising the Climb
         */
        public static final int UPPIES = 8;
        /**
         * Button for Lowering the Climb
         */
        public static final int DOWNS = 7;
        /**
         * Button for Spinning the Flywheels and Feeder Inwards
         */
        public static final int FEEDER_IN = 3;
        public static final int SLURP = 5;
        /**
         * Button for Spinning the Flywheels Outwards
         */
        public static final int SPIT = 2;
        /**
         * Button for Spinning the Feeder Wheels Outwards
         */
        public static final int FEEDER_OUT = 1;
    }
}
