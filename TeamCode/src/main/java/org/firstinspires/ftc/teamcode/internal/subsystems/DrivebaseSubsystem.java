package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.internal.auto.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.auto.commands.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.internal.util.EncoderConstants;
import org.firstinspires.ftc.teamcode.internal.util.MotorSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a drivebase that handles all movement functions of a robot.
 * Is a subclass of {@link CustomSubsystemBase} to achieve a command-based design pattern.
 *
 * @author Esquimalt Atom Smashers
 */
public class DrivebaseSubsystem extends SubsystemBase {
    /** Motors which control the drivebase */
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor rearLeft;
    private final DcMotor rearRight;

    private DcMotor[] motors;

    private final List<CommandBase> drivenCommands = new ArrayList<>();


//    private final RevIMU gyro;

    public DrivebaseSubsystem(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

        motors = new DcMotor[]{frontLeft, frontRight, rearLeft, rearRight};

        Arrays.stream(motors).forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.FORWARD);
        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
    }

    public void drive(double left_x, double left_y, double right_x) {
        frontLeft.setPower(Range.clip(left_y + left_x + right_x, -1, 1));
        frontRight.setPower(Range.clip(left_y - left_x - right_x, -1, 1));
        rearLeft.setPower(Range.clip(left_y - left_x + right_x, -1, 1));
        rearRight.setPower(Range.clip(left_y + left_x - right_x, -1, 1));
    }

    /**
     * Autonomously drives the robot forwards given the entered distance.
     *
     * @param cm the distance the robot will drive
     */
    public void driveT(int cm) {
        Arrays.stream(motors)
                .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                        + (int)(cm * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE)));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_TO_POSITION));

        drive(0, 0.2, 0);
        while(frontLeft.isBusy() && frontRight.isBusy() && rearLeft.isBusy() && rearRight.isBusy()) {

        }

        Arrays.stream(motors)
                .forEach(motor -> motor.setPower(0.0));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER));


    }

    /**
     * Autonomously strafes the robot given the entered distance and position
     *
     * @param cm the distance the robot will strafe
     * @param left strafing left or right
     */
    public void strafe(int cm, boolean left) {

//        MotorSettings.resetEncoders(frontLeft, frontRight, rearLeft, rearRight); //Reset encoders so no funny business happens
//
//        int target = frontLeft.getCurrentPosition() + (int) (cm * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE); //Get the project target position (in pulses)
//
//        MotorSettings.setTargetPositions(target, frontLeft, frontRight, rearLeft, rearRight); //Sets the target position for all the motors
//        MotorSettings.setMotors(0, frontLeft, frontRight, rearLeft, rearRight); //Sets the power of all the motors to 0
//
//        mecanum.driveRobotCentric(left ? -0.5 : 0.5, 0, 0);
//        while (!frontLeft.atTargetPosition()) {
//
//        }
//        MotorSettings.stopMotors(frontLeft, frontRight, rearLeft, rearRight); //Stop all motors
//
//        drivenCommands.add(new StrafeDriveCommand(this, cm, !left));
    }

    public void returnToHome() {
        for (Command command : drivenCommands) {
            command.execute();
        }
        drivenCommands.clear();
    }
}

