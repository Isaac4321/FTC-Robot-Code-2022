package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.internal.util.EncoderConstants;
import org.firstinspires.ftc.teamcode.internal.util.GyroConstants;

import java.util.Arrays;

/**
 * Represents a drivebase that handles all movement functions of a robot.
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

    /** The built-in gyro on the control hub */
    private BNO055IMU gyro;

    /** Speeds in which the robot strafes and drives */
    private final double AUTO_STRAFE_SPEED = 0.2;
    private final double AUTO_DRIVE_SPEED = 0.5;

    /** Enum used for driving in different units of length */
    public enum DistanceUnits {
        CENTIMETRES,
        INCHES,
        TILES
    }

    /**
     * The sole constructor of DrivebaseSubsystem. Initializes the 4 drivebase motors
     * and the built-in gyro. The drivebase motors are set to their corresponding directions and
     * run-modes. On top of this, the gyro parameters are set {@link BNO055IMU.Parameters}.
     *
     * @param hardwareMap the robot's hardwareMap
     */
    public DrivebaseSubsystem(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");

        motors = new DcMotor[]{frontLeft, frontRight, rearLeft, rearRight};

//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//
        gyro = hardwareMap.get(BNO055IMU.class, "imu");
//        gyro.initialize(parameters);

        Arrays.stream(motors).forEach(motor -> motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE));

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.FORWARD);

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
    }

    /**
     * Drives the robot given the 3 joystick positions. The drivebase uses mecanum wheels allowing for 8 directional travel.
     *
     * @param strafe how far the robot will strafe left or right
     * @param forward how far the robot will drive forward or backwards
     * @param turn how much the robot will turn clockwise or counterclockwise
     */
    public void drive(double strafe, double forward, double turn) {
        frontLeft.setPower(Range.clip(forward + strafe + turn, -1, 1));
        frontRight.setPower(Range.clip(forward - strafe - turn, -1, 1));
        rearLeft.setPower(Range.clip(forward - strafe + turn, -1, 1));
        rearRight.setPower(Range.clip(forward + strafe - turn, -1, 1));
    }

    public BNO055IMU getGyro() {
        return gyro;
    }

    /**
     * Drives the robot autonomously forwards or backwards given the entered distance. Will auto adjust itself based on heading.
     *
     * @param unit the unit of measurement
     * @param distance the distance the robot will drive
     * @param heading the heading the robot will adjust itself based on
     */
    public void drive(DistanceUnits unit, double distance, double heading) {
        double turn = 0;

        switch (unit) {
            case CENTIMETRES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int)(distance * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE)));
                break;
            case INCHES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int)(distance * EncoderConstants.Gobilda312RPM.PULSES_PER_INCH)));
                break;
            case TILES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int)(distance * EncoderConstants.Gobilda312RPM.PULSES_PER_TILE)));
                break;
        }

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_TO_POSITION));

        drive(0, AUTO_DRIVE_SPEED, 0);
        while(frontLeft.isBusy() && frontRight.isBusy() && rearLeft.isBusy() && rearRight.isBusy()) {
            turn = getSteeringCorrection(heading, GyroConstants.P_DRIVE_GAIN);

            if (distance < 0) {
                turn *= -1.0;
            }

            drive(0, AUTO_DRIVE_SPEED, turn);
        }

        Arrays.stream(motors)
                .forEach(motor -> motor.setPower(0));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
    }

    public void strafe(DistanceUnits unit, int distance) {
        switch (unit) {
            case CENTIMETRES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int) (distance * EncoderConstants.Gobilda312RPM.PULSES_PER_CENTIMETRE)));
                break;
            case INCHES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int) (distance * EncoderConstants.Gobilda312RPM.PULSES_PER_INCH)));
                break;
            case TILES:
                Arrays.stream(motors)
                        .forEach(motor -> motor.setTargetPosition(motor.getCurrentPosition()
                                + (int) (distance * EncoderConstants.Gobilda312RPM.PULSES_PER_TILE)));
                break;
        }

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_TO_POSITION));

        drive(AUTO_STRAFE_SPEED, 0, 0);
        while (frontLeft.isBusy() && frontRight.isBusy() && rearLeft.isBusy() && rearRight.isBusy()) {

        }

        Arrays.stream(motors)
                .forEach(motor -> motor.setPower(0.0));

        Arrays.stream(motors)
                .forEach(motor -> motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER));
        }

    public void rotate(double heading) {
        double headingError = getHeadingError(heading);
        double turn = 0.0;

        while (Math.abs(headingError) > 1.0) {
            turn = getSteeringCorrection(heading, GyroConstants.P_TURN_GAIN);
            drive(0, 0, turn);
        }
    }

    private double getHeadingError(double desiredHeading) {
        double headingError = desiredHeading - (getRawHeading() - 0.0); // 0.0 is the heading offset

        while (headingError > 180) {
            headingError -= 360;
        }

        while (headingError < -180) {
            headingError += 360;
        }

        return headingError;
    }

    private double getSteeringCorrection(double desiredHeading, double proportionalGain) {
        double headingError = desiredHeading - (getRawHeading() - 0.0); // 0.0 is the heading offset

        while (headingError > 180) {
            headingError -= 360;
        }

        while (headingError < -180) {
            headingError += 360;
        }

        return Range.clip(headingError * proportionalGain, -1, 1);
    }

    private double getRawHeading() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return angles.firstAngle;
    }
}

