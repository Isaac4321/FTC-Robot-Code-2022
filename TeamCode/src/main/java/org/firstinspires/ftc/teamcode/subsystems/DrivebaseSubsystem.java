package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.MotorSettings;

/**
 * Represents a drivebase that handles all movement functions of a robot.
 * Is a subclass of {@link CustomSubsystemBase} to achieve a command-based design pattern.
 *
 * @author Esquimalt Atom Smashers
 */
public class DrivebaseSubsystem extends CustomSubsystemBase {
    /** The type of motors the drive base is running */
    private final Motor.GoBILDA motorType = Motor.GoBILDA.RPM_60;

    /** A MecanumDrive instance which handles the driving of the robot */
    private final MecanumDrive mecanum;

    /** Motors which control the drivebase */
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor rearLeft;
    private final Motor rearRight;

    /**
     * Sole constructor of DrivebaseSubsystem.
     *
     * Initializes all motors, encoders and mecanum drive and
     * sets them to the proper configurations based on hardware map.
     * Sets all the proper motor modes i.e brake mode and running with encoders.
     *
     * @param hardwareMap the hardware map of the robot
     * @param auto whether or not the robot is in autonomous mode
     */
    public DrivebaseSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);

        frontLeft = new Motor(hardwareMap, "frontLeft", motorType);
        frontRight = new Motor(hardwareMap, "frontRight", motorType);
        rearLeft = new Motor(hardwareMap, "rearLeft", motorType);
        rearRight = new Motor(hardwareMap, "rearRight", motorType);

        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight);

        if (auto) {
            MotorSettings.setZeroPowerBehaviors(MotorSettings.defaultZeroPowerBehavior, frontLeft, frontRight, rearLeft, rearRight);
            MotorSettings.setMotorRunModes(MotorSettings.autoRunMode, frontLeft, frontRight, rearLeft, rearRight);
        }
        else {
            MotorSettings.setZeroPowerBehaviors(MotorSettings.defaultZeroPowerBehavior, frontLeft, frontRight, rearLeft, rearRight);
            MotorSettings.setMotorRunModes(MotorSettings.defaultRunMode, frontLeft, frontRight, rearLeft, rearRight);
        }
        mecanum = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);
    }

    @Override
    public String getTask() {
        return "Handles all the movement of the robot.";
    }

    /**
     * Drives the robot in the given direction based on the three given parameters
     * Calls the {@link MecanumDrive#driveRobotCentric(double, double, double)}
     *
     * @param left_x the value of the left joystick x axis
     * @param left_y the value of the left joystick y axis
     * @param right_x the value of the right joystick x axis
     */
    public void drive(double left_x, double left_y, double right_x) {
        mecanum.driveRobotCentric(left_x, left_y, right_x);
    }

    public void forwardDrive(int cm) {
        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens

        int target = frontLeft.getCurrentPosition() + (int) (cm * 100); //Get the project target position (in pulses)

        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position (in pulses) for all the motors
        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0

        while (!frontLeft.atTargetPosition()) { //Keep powering the motors until they reach their target position
            MotorSettings.setMotors(0.5, frontLeft, frontRight, rearLeft, rearRight);
        }

        MotorSettings.stopMotors(frontLeft, frontRight, rearLeft, rearRight); //Stop all motors
    }

    public void backwardDrive(int cm) {
        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens

        int target = frontLeft.getCurrentPosition() + (int) (cm * 100); //Get the project target position (in pulses)

        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position for all the motors
        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0

        while (!frontLeft.atTargetPosition()) { //Keep powering the motors until they reach their target position
            MotorSettings.setMotors(0.5, frontLeft, frontRight, rearLeft, rearRight);
        }

        MotorSettings.stopMotors(frontLeft, frontRight, rearLeft, rearRight); //Stop all motors
    }

    public void strafeDrive(int cm, boolean left) {
        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens

        int target = frontLeft.getCurrentPosition() + (int) (cm * 100); //Get the project target position (in pulses)

        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position for all the motors
        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0

        while (!frontLeft.atTargetPosition()) {
            mecanum.driveRobotCentric(left ? -0.5 : 0.5, 0, 0);
        }
    }
}

