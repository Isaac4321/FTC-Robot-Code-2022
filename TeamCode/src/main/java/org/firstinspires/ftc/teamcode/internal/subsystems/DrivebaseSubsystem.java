package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.internal.auto.commands.AutonomousCommandBase;
import org.firstinspires.ftc.teamcode.internal.auto.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.auto.commands.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.internal.util.EncoderConstants;
import org.firstinspires.ftc.teamcode.internal.util.MotorSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a drivebase that handles all movement functions of a robot.
 * Is a subclass of {@link CustomSubsystemBase} to achieve a command-based design pattern.
 *
 * @author Esquimalt Atom Smashers
 */
public class DrivebaseSubsystem extends CustomSubsystemBase {
    /** The type of motors the drive base is running */
    private final Motor.GoBILDA motorType = Motor.GoBILDA.RPM_312;

    /** A MecanumDrive instance which handles the driving of the robot */
    private final MecanumDrive mecanum;

    /** Motors which control the drivebase */
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor rearLeft;
    private final Motor rearRight;

    private final List<AutonomousCommandBase> drivenCommands = new ArrayList<>();

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

    /**
     * Autonomously drives the robot forwards given the entered distance.
     *
     * @param cm the distance the robot will drive
     */
    public void drive(int cm) {
        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens

        int target = frontLeft.getCurrentPosition() + (int) (cm * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE); //Get the project target position (in pulses)

        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position (in pulses) for all the motors
        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0

        MotorSettings.setMotors(0.5, frontLeft, frontRight, rearLeft, rearRight);
        while (!frontLeft.atTargetPosition()) { //Keep powering the motors until they reach their target position

        }
        MotorSettings.stopMotors(frontLeft, frontRight, rearLeft, rearRight); //Stop all motors

        drivenCommands.add(new DriveCommand(this, cm * -1));
    }

    /**
     * Autonomously strafes the robot given the entered distance and position
     *
     * @param cm the distance the robot will strafe
     * @param left strafing left or right
     */
    public void strafe(int cm, boolean left) {

        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens

        int target = frontLeft.getCurrentPosition() + (int) (cm * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE); //Get the project target position (in pulses)

        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position for all the motors
        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0

        mecanum.driveRobotCentric(left ? -0.5 : 0.5, 0, 0);
        while (!frontLeft.atTargetPosition()) {

        }
        MotorSettings.stopMotors(frontLeft, frontRight, rearLeft, rearRight); //Stop all motors

        drivenCommands.add(new StrafeDriveCommand(this, cm, !left));
    }

    public void returnToHome() {
        for (Command command : drivenCommands) {
            command.execute();
        }
        drivenCommands.clear();
    }
}

