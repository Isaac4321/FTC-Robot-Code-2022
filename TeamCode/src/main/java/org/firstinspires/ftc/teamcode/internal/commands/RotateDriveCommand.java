package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class RotateDriveCommand extends CommandBase {

    private DrivebaseSubsystem drivebaseSubsystem;

    private double heading;

    public RotateDriveCommand(DrivebaseSubsystem subsystem, double heading) {
        drivebaseSubsystem = subsystem;
        this.heading = heading;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.rotate(heading);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
