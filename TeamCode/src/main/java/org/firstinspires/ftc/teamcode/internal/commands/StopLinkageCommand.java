package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LinkageSubsystem;

public class StopLinkageCommand extends CommandBase {

    private final LinkageSubsystem linkageSubsystem;

    public StopLinkageCommand(LinkageSubsystem subsystem) {
        linkageSubsystem = subsystem;
        addRequirements(linkageSubsystem);
    }

    @Override
    public void initialize() {
        linkageSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
