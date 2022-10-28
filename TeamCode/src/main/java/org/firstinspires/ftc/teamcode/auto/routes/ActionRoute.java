package org.firstinspires.ftc.teamcode.auto.routes;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.auto.commands.AutonomousCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinkageSubsystem;

public class ActionRoute extends SequentialCommandGroup {

    public ActionRoute(LinkageSubsystem linkageSubsystem, ClawSubsystem clawSubsystem, CommandBase... commands) {
        addCommands(commands);
        addRequirements(linkageSubsystem, clawSubsystem);
    }

}
