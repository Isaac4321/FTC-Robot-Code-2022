package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.ClawSubsystem;

public class OpenClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    public OpenClawCommand(ClawSubsystem subsystem) {
        clawSubsystem = subsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        clawSubsystem.openClaw();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
