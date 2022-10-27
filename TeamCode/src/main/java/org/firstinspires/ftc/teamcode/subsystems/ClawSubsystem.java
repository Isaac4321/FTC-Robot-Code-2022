package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Represents a claw that opens and closes via the control of a servo
 * Is a subclass of {@link CustomSubsystemBase} to achieve a command-based design pattern.
 *
 * @author Esquimalt Atom Smashers
 */
public class ClawSubsystem extends CustomSubsystemBase {
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
     * @param auto
     */
    public ClawSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);
        claw = new SimpleServo(hardwareMap, "clawServo", MINIMUM_ANGLE, MAXIMUM_ANGLE, AngleUnit.DEGREES);
    }

    /**
     * Returns a string which explains the task of the subsystem.
     *
     * @return a string that explains the task of the given subsystem
     */
    @Override
    public String getTask() {
        return "Handles the movement of the claw i.e opening and closing";
    }

    /**
     * Opens the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void openClaw() {
        claw.rotateByAngle(90);
    }

    /**
     * Closes the claw by rotating the servo
     * Calls the {@link SimpleServo#rotateByAngle(double) method}
     */
    public void closeClaw() {
        claw.rotateByAngle(-90);
    }
}
