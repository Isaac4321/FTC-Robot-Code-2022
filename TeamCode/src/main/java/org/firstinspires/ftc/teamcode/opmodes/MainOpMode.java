package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MyRobot;

@SuppressWarnings("unused")
public class MainOpMode extends LinearOpMode {

    private MyRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = MyRobot.getRobotInstance(MyRobot.OpModeType.TELE_OP, this);

        while (opModeIsActive() && !isStopRequested()) {
            robot.run();
        }
    }

    public MyRobot getRobot() {
        return robot;
    }
}
