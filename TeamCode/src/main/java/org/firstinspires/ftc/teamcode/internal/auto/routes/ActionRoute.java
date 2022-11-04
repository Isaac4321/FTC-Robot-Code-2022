package org.firstinspires.ftc.teamcode.internal.auto.routes;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.internal.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LinkageSubsystem;

public class ActionRoute extends SequentialCommandGroup {

    public ActionRoute(LinkageSubsystem linkageSubsystem, ClawSubsystem clawSubsystem, CommandBase... commands) {
        addCommands(commands);
        addRequirements(linkageSubsystem, clawSubsystem);
    }

}
