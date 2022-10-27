package org.firstinspires.ftc.teamcode.util;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public final class MotorSettings {
    /** The default zero power behavior of the motors i.e BRAKE, FLOAT, UNKNOWN */
    public static final Motor.ZeroPowerBehavior defaultZeroPowerBehavior = Motor.ZeroPowerBehavior.BRAKE;

    /** The default run-mode of the motors i.e RawPower, VelocityControl, PositionalControl */
    public static final Motor.RunMode defaultRunMode = Motor.RunMode.RawPower;

    /** The autonomous run-mode of the motors */
    public static final Motor.RunMode autoRunMode = Motor.RunMode.PositionControl;

    /**
     * Sets the run-mode for the given motors
     *
     * @param runMode the runMode that is being set
     * @param motors the motors who's run-mode is being set
     */
    public static void setMotorRunModes(Motor.RunMode runMode, Motor... motors) {
        for (Motor motor : motors) {
            motor.setRunMode(runMode);
        }
    }

    /**
     * Sets the zero power behavior of the given motors
     *
     * @param zeroPowerBehavior the zeroPowerBehavior that is being set
     * @param motors the motors who's zero power behavior is being set
     */
    public static void setZeroPowerBehaviors(Motor.ZeroPowerBehavior zeroPowerBehavior, Motor... motors) {
        for (Motor motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }

    public static void setTargetDistance(double pos, Motor... motors) {
        for (Motor motor: motors) {
            motor.setTargetDistance(pos);
        }
    }
}
