package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.subsystems.LinkageSubsystem;

public class LinkagePositioningCommand extends AutonomousCommand {

    private final LinkageSubsystem linkageSubsystem;
    private final LinkageSubsystem.Position position;

    public LinkagePositioningCommand(LinkageSubsystem subsystem, LinkageSubsystem.Position pos) {
        linkageSubsystem = subsystem;
        position = pos;
        addRequirements(linkageSubsystem);
    }

    @Override
    public void initialize() {
        linkageSubsystem.goTo(position);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
