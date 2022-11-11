package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.internal.Robot;

@Autonomous
public class AutoOpMode extends CommandOpMode {
    @Override
    public void initialize() {
        Robot robot = Robot.getRobotInstance(Robot.OpModeType.AUTO_OP, this);


//        schedule(new AdvancedRoute(robot.getSubsystems(),
//                new DriveRoute(robot.getDrivebaseSubsystem(),
//                        new ForwardDriveCommand(robot.getDrivebaseSubsystem(), 10)
//                )));
    }
}
