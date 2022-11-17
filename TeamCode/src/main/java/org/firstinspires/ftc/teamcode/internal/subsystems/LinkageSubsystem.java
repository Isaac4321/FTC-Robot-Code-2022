package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.internal.util.EncoderConstants;

/**
 * Represents a 4 bar linkage which handles lifting the claw.
 *
 *
 * @author Esquimalt Atom Smashers
 */
public class LinkageSubsystem extends SubsystemBase {

    /** The speed of which the motors run at */
    private final double LIFT_SPEED = 0.60;
    private final double DROP_SPEED = -0.25;

    /** The current position of the linkage */
    private Position currentPosition;

    private Position[] positions = {Position.HOME, Position.SMALL_POLE, Position.MEDIUM_POLE, Position.TALL_POLE};

    private int cursor;

    /** The motors that control the linkage */
    private final DcMotor linkage;

    /**
     * Enum of all target positions the linkage can go to.
     * Used in autonomous and for convenience during tele-op periods
     */
    public enum Position {
        HOME(0),
        SMALL_POLE(45),
        MEDIUM_POLE(90),
        TALL_POLE(110);

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
     */
    public LinkageSubsystem(HardwareMap hardwareMap) {
        linkage = hardwareMap.get(DcMotor.class, "leftLinkageMotor");

        currentPosition = Position.HOME;
        cursor = 0;

        linkage.setDirection(DcMotorSimple.Direction.REVERSE);
        linkage.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        linkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /** Lifts the linkage at a given speed */
    public void lift() {
        linkage.setPower(LIFT_SPEED);
    }

    /** Drops the linkage at a given speed */
    public void drop() {
        linkage.setPower(DROP_SPEED);
    }

    /** Stops the linkage from lifting or dropping */
    public void stop() {
        linkage.setPower(0);
    }

    public void nextPos() {
        if (currentPosition == Position.TALL_POLE) {
            return;
        }
        cursor++;
        linkage.setTargetPosition((int) (positions[cursor].deg * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE));

        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linkage.setPower(LIFT_SPEED);
        while (linkage.isBusy()) {

        }
        linkage.setPower(0);

        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = positions[cursor];
    }

    public void prevPos() {
        if (currentPosition == Position.HOME) {
            return;
        }
        cursor--;
        linkage.setTargetPosition((int) (positions[cursor].deg * EncoderConstants.Gobilda60RPM.PULSES_PER_DEGREE));

        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linkage.setPower(0.25);
        while (linkage.isBusy()) {

        }

        linkage.setPower(0);

        linkage.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = positions[cursor];
    }

    /**
     * @return the current position of the linkage
     */
    public Position getCurrentPosition() {
        return currentPosition;
    }

}
