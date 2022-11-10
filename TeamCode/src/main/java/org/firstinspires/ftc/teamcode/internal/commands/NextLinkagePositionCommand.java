package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LinkageSubsystem;

public class NextLinkagePositionCommand extends CommandBase {

    private LinkageSubsystem linkageSubsystem;

    public NextLinkagePositionCommand(LinkageSubsystem subsystem) {
        linkageSubsystem = subsystem;
        addRequirements(subsystem);
    }

    public void nextPos() {
        linkageSubsystem.nextPos();
    }

}
