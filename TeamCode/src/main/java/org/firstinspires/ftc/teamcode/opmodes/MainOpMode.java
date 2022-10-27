package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MyRobot;

@SuppressWarnings("unused")
@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class MainOpMode extends LinearOpMode {
    private MyRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = MyRobot.getRobotInstance(MyRobot.OpModeType.TELE_OP, this);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }

    public MyRobot getRobot() {
        return robot;
    }
}
