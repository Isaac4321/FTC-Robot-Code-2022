package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.internal.Robot;

@SuppressWarnings("unused")
@TeleOp(name="Main Opmode", group="Linear Opmode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = Robot.getRobotInstance(Robot.OpModeType.TELE_OP, this);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        robot.getDrivebaseSubsystem().getGyro().initialize(parameters);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }

    public Robot getRobot() {
        return robot;
    }
}
