package org.firstinspires.ftc.teamcode.internal.auto.routes;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.internal.auto.commands.AutonomousCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class DriveRoute extends SequentialCommandGroup {

    public DriveRoute(DrivebaseSubsystem drivebaseSubsystem, AutonomousCommand... autonomousCommands) {
        addCommands(autonomousCommands);
        addRequirements(drivebaseSubsystem);
    }

}
