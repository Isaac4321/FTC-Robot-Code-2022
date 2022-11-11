package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LinkageSubsystem;

public class PrevLinkagePositionCommand extends CommandBase {

    private LinkageSubsystem linkageSubsystem;

    public PrevLinkagePositionCommand(LinkageSubsystem subsystem) {
        linkageSubsystem = subsystem;
        addRequirements(linkageSubsystem);
    }

    @Override
    public void initialize() {
        linkageSubsystem.prevPos();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}