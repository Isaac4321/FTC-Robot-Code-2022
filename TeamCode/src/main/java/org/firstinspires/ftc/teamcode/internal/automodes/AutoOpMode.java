package org.firstinspires.ftc.teamcode.internal.automodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.auto.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.CloseClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOnCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;

@Autonomous
public class AutoOpMode extends CommandOpMode {
    @Override
    public void initialize() {
        Robot robot = Robot.getRobotInstance(Robot.OpModeType.AUTO_OP, this);


        /*
            Closes claw then drives forward for two tiles (48 inches) then turns on the under-glow LED's
         */
        schedule(
                new CloseClawCommand(robot.getClawSubsystem()),
                new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.TILES, 2, 0.0),
                new LightOnCommand(robot.getLightSubsystem(), LightSubsystem.LightType.UNDER_GLOW));

    }
}
