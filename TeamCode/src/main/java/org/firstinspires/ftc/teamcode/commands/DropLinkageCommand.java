package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.LinkageSubsystem;

public class DropLinkageCommand extends CommandBase {

    private final LinkageSubsystem linkageSubsystem;

    public DropLinkageCommand(LinkageSubsystem subsystem) {
        linkageSubsystem = subsystem;
        addRequirements(linkageSubsystem);
    }

    @Override
    public void execute() {
        linkageSubsystem.drop();
    }
}
