package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.ClawSubsystem;

public class CloseClawCommand extends CommandBase {

    private final ClawSubsystem clawSubsystem;

    public CloseClawCommand(ClawSubsystem subsystem) {
        clawSubsystem = subsystem;
        addRequirements(clawSubsystem);
    }

    @Override
    public void initialize() {
        clawSubsystem.closeClaw();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
