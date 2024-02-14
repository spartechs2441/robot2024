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
        public static final int BACK_LEFT_DRIVE = -1;
        public static final int FRONT_LEFT_DRIVE = -1;
        public static final int FRONT_RIGHT_DRIVE = -1;
        public static final int BACK_RIGHT_DRIVE = -1;

        // robot has not been built so change these later
        public static final int INTAKE = 2;
        public static final int INTAKE_HINGE = 3;
        public static final int INTAKE_TOWER = 1;
        public static final int SHOOTER_RIGHT = -1;
        public static final int SHOOTER_LEFT = -1;
        public static final int CONVEYOR = -1;
        public static final int LEFT_CLIMB = -1;
        public static final int RIGHT_CLIMB = -1;
    }

    /**
     * Multiplies the values when driving
     * To avoid runtime errors, do not set these fields above 1
     */
    public static class Speed {
        public static final double AUTO = .6;
        public static final double TELEOP = 1;
        public static final double TELEOP_ROTATION = 1;
        public static final double SHOOTER = 8.5+.5;
        public static final double CONVEYOR = 1.5;
        public static final double CLIMB = 1;
   }

    /**
     * Contains voltages of all the motors,
     */
    public static class Voltage {
        // TODO Change these values
        public static final double DEPLOY = 2;
        public static final double INTAKE = 3;
        public static final double INTAKE_TOWER = 9;
    }

    public static class Buttons {
        /**
         * Button for sucking a note in
         */
        public static final int INTAKE = 9;
        /**
         * Button for spitting a note out
         */
        public static final int EJECT = 10;
        /**
         * Button for deploying the intake
         */
        public static final int DEPLOY = 8;
        /**
         * Button for putting up the intake
         */
        public static final int RETRACT = 7;
        /**
         * Button for Raising the intake tower
         */
        public static final int RISE = 11;
        /**
         * Button for Dropping the intake tower
         */
        public static final int DROP = 12;

    }
}
