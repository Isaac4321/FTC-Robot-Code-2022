package org.firstinspires.ftc.teamcode.internal.automodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.auto.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.auto.commands.StrafeDriveCommand;
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
                new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 12, 0.0),
                new StrafeDriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 12),
                new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, -12, 0.0),
                new StrafeDriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, -12));

    }
}
