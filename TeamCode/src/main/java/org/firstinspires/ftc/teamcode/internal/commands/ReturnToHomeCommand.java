package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class ReturnToHomeCommand extends CommandBase {

    private DrivebaseSubsystem drivebaseSubsystem;

    public ReturnToHomeCommand(DrivebaseSubsystem subsystem) {
        drivebaseSubsystem = subsystem;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.returnToHome();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
