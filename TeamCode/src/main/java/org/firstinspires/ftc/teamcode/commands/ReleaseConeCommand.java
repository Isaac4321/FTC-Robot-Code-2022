package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;

public class ReleaseConeCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    public ReleaseConeCommand(ClawSubsystem subsystem) {
        clawSubsystem = subsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        clawSubsystem.releaseCone();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
