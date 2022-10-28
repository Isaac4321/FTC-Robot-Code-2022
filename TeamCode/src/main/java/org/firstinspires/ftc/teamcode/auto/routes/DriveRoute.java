package org.firstinspires.ftc.teamcode.auto.routes;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.auto.commands.AutonomousCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class DriveRoute extends SequentialCommandGroup {

    public DriveRoute(DrivebaseSubsystem drivebaseSubsystem, AutonomousCommand... autonomousCommands) {
        addCommands(autonomousCommands);
        addRequirements(drivebaseSubsystem);
    }

}
