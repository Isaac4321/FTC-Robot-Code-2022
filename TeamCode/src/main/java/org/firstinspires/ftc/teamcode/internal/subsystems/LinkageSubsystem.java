package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.internal.util.EncoderConstants;
import org.firstinspires.ftc.teamcode.internal.util.MotorSettings;

/**
 * Represents a 4 bar linkage which handles lifting the claw.
 * Is a subclass of {@link CustomSubsystemBase} to achieve a command-based design pattern.
 *
 * @author Esquimalt Atom Smashers
 */
public class LinkageSubsystem extends CustomSubsystemBase {

    /** The speed of which the motors run at */
    private final double LIFT_SPEED_PERCENTAGE = 0.50;
    private final double DROP_SPEED_PERCENTAGE = 0.10;

    /** The type of motors the linkage is running */
    private final Motor.GoBILDA motorType = Motor.GoBILDA.RPM_60;

    /** The current position of the linkage */
    private Position currentPosition;

    private Position[] positions = {Position.HOME, Position.SMALL_POLE, Position.MEDIUM_POLE, Position.TALL_POLE};

    private int cursor;

    /** The motors that control the linkage */
    private final DcMotor leftMotor;

    /**
     * Enum of all target positions the linkage can go to.
     * Used in autonomous and for convenience during tele-op periods
     */
    public enum Position {
        HOME(0),
        SMALL_POLE(200),
        MEDIUM_POLE(250),
        TALL_POLE(300);

        private int deg;

        Position(int deg) {
            this.deg = deg;
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

        leftMotor = hardwareMap.get(DcMotor.class, "leftLinkageMotor");


        currentPosition = Position.HOME;
        cursor = 0;

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        if (auto) {
//            leftMotor.setRunMode(Motor.RunMode.PositionControl);
//            leftMotor.setDistancePerPulse(EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE);
//        }
//        else {
//            leftMotor.setRunMode(MotorSettings.defaultRunMode);
//
//        }
    }

    /** Lifts the linkage at a given speed */
    public void lift() {
        leftMotor.setPower(LIFT_SPEED_PERCENTAGE);
    }

    /** Drops the linkage at a given speed */
    public void drop() {
        leftMotor.setPower(-DROP_SPEED_PERCENTAGE);
    }

    /** Stops the linkage from lifting or dropping */
    public void stop() {
        leftMotor.setPower(0);
    }

    public void nextPos() {
//        if (currentPosition == Position.TALL_POLE) {
//            return;
//        }
//        cursor++;
//        leftMotor.setTargetPosition(positions[cursor].deg);
//
//        while (!leftMotor.atTargetPosition()) {
//            leftMotor.set(0.5);
//        }
//
//        leftMotor.stopMotor();
//
//        currentPosition = positions[cursor];
    }

    public void prevPos() {
//        if (currentPosition == Position.HOME) {
//            return;
//        }
//        cursor--;
//        leftMotor.setTargetPosition((int) (-positions[cursor].deg * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE));
//
//        leftMotor.set(0.1);
//        while (!leftMotor.atTargetPosition()) {
//
//        }
//        leftMotor.stopMotor();
//
//        currentPosition = positions[cursor];
    }

    /**
     * @return the current position of the linkage
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

}
