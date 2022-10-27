package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.MotorSettings;

public class LinkageSubsystem extends CustomSubsystemBase {

    /** The speed of which the motors run at */
    private final double LIFT_SPEED_PERCENTAGE = 0.10;
    private final double DROP_SPEED_PERCENTAGE = 0.10;

    /** The type of motors the linkage is running */
    private final Motor.GoBILDA motorType = Motor.GoBILDA.RPM_312;

    /** The current position of the linkage */
    private final Position currentPosition;

    /** The motors that control the linkage */
    private final Motor leftMotor;
    private final Motor rightMotor;

    /** The matching motor encoders for the linkage motors */
    private final Motor.Encoder leftEncoder;
    private final Motor.Encoder rightEncoder;

    /**
     * Enum of all target positions the linkage can go to.
     * Used in autonomous and for convince during tele-op periods
     */
    public enum Position {
        HOME(0),
        SMALL_POLE(10),
        MEDIUM_POLE(25),
        TALL_POLE(30);

        private int encoderPosition;

        Position(int encoderPosition) {
            this.encoderPosition = encoderPosition;
        }

        public int getEncoderPosition() {
            return encoderPosition;
        }
    }

    /**
     * Sole constructor of LinkageSubsystem.
     *
     * Initializes all motors and encoders and
     * sets them to the proper configurations based on {@param hardwareMap}
     * Sets all the proper motor modes i.e brake mode and running with encoders.
     *
     * @param hardwareMap the hardware map of the robot
     * @param auto whether or not the robot is in autonomous mode
     */
    public LinkageSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);

        leftMotor = new Motor(hardwareMap, "leftLinkageMotor", motorType);
        rightMotor = new Motor(hardwareMap, "rightLinkageMotor", motorType);

        //Should be 1 cm per pulse
        leftMotor.setDistancePerPulse(1.0 / 2786.2);
        rightMotor.setDistancePerPulse(1.0 / 2786.2);

        leftMotor.resetEncoder();
        rightMotor.resetEncoder();

        leftEncoder = leftMotor.encoder;
        rightEncoder = rightMotor.encoder;

        currentPosition = Position.HOME;

        if (auto) {
            MotorSettings.setZeroPowerBehaviors(MotorSettings.defaultZeroPowerBehavior, leftMotor, rightMotor);
            MotorSettings.setMotorRunModes(MotorSettings.autoRunMode, leftMotor, rightMotor);
        }
        else {
            MotorSettings.setZeroPowerBehaviors(MotorSettings.defaultZeroPowerBehavior, leftMotor, rightMotor);
            MotorSettings.setMotorRunModes(MotorSettings.defaultRunMode, leftMotor, rightMotor);
        }
    }

    /**
     * Returns a string which explains the task of the subsystem.
     *
     * @return a string that explains the task of the given subsystem
     */
    @Override
    public String getTask() {
        return "Handles the lifting of the linkage on the robot.";
    }

    public void lift() {
        leftMotor.set(LIFT_SPEED_PERCENTAGE);
        rightMotor.set(LIFT_SPEED_PERCENTAGE);
    }

    public void drop() {
        leftMotor.set(-DROP_SPEED_PERCENTAGE);
        rightMotor.set(-DROP_SPEED_PERCENTAGE);
    }

    public void stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

    public void goTo(Position pos) {
        switch (pos) {
            case HOME:
                MotorSettings.setTargetDistance(Position.HOME.getEncoderPosition(), leftMotor, rightMotor);
                break;
            case SMALL_POLE:
                MotorSettings.setTargetDistance(Position.SMALL_POLE.getEncoderPosition(), leftMotor, rightMotor);
                break;
            case MEDIUM_POLE:
                MotorSettings.setTargetDistance(Position.MEDIUM_POLE.getEncoderPosition(), leftMotor, rightMotor);
                break;
            case TALL_POLE:
                MotorSettings.setTargetDistance(Position.TALL_POLE.getEncoderPosition(), leftMotor, rightMotor);
                break;
        }

        leftMotor.set(LIFT_SPEED_PERCENTAGE);
        leftMotor.set(LIFT_SPEED_PERCENTAGE);
    }
}
