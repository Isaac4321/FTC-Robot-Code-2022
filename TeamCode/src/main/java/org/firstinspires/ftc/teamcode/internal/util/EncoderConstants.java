package org.firstinspires.ftc.teamcode.internal.util;

public class EncoderConstants {
    public static class Gobilda312RPM {
        public static final double PULSES_PER_MOTOR_REV = 537.7;
        public static final double DRIVE_GEAR_REDUCTION = 1.0;

        public static final double WHEEL_DIAMETER_CM = 10.16;
        public static final double WHEEL_DIAMETER_INCHES = 4.0;

        public static final double PULSES_PER_CENTIMETRE =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CM * Math.PI);
        public static final double PULSES_PER_INCH =
                (PULSES_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
        public static final double PULSES_PER_TILE = (PULSES_PER_INCH * 24);
    }

    public static class Gobilda60RPM {
        public static final double PULSES_PER_MOTOR_REV = 2786.2;
        public static final double DRIVE_GEAR_REDUCTION = 1.0;
        public static final double WHEEL_DIAMETER_CM = 10.16;
        public static final double PULSES_PER_DEGREE = 2786.2 / 360;
    }
}
