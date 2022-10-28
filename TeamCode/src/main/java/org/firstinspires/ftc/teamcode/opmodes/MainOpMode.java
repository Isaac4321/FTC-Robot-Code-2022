package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

@SuppressWarnings("unused")
@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class MainOpMode extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = Robot.getRobotInstance(Robot.OpModeType.TELE_OP, this);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }

    public Robot getRobot() {
        return robot;
    }
}
