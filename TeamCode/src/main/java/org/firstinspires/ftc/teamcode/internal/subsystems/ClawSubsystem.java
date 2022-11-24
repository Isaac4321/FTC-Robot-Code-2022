package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Represents a claw that opens and closes via the control of a servo
 *
 *
 * @author Esquimalt Atom Smashers
 */
public class ClawSubsystem extends SubsystemBase{
    /** The minimum angle of the servo */
    private final int MINIMUM_ANGLE = 0;

    /** The maximum angle of the servo */
    private final int MAXIMUM_ANGLE = 270;

    /** The servo which controls the claw*/
    private final ServoEx claw;

    /**
     * Sole constructor of ClawSubsystem.
     *
     * Initializes the claw servo and
     * sets to the proper configurations based on the hardware map.
     *
     * @param hardwareMap
     */
    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = new SimpleServo(hardwareMap, "clawServo", MINIMUM_ANGLE, MAXIMUM_ANGLE, AngleUnit.DEGREES);
        claw.setPosition(0);
    }

    /**
     * Opens the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void openClaw() {
        claw.turnToAngle(25);
    }

    /**
     * Closes the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void closeClaw() {
        claw.turnToAngle(80);
    }
}
