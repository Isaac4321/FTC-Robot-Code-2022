package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class StrafeDriveCommand extends CommandBase {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final DrivebaseSubsystem.DistanceUnits unit;
    private final int distance;

    public StrafeDriveCommand(DrivebaseSubsystem subsystem, DrivebaseSubsystem.DistanceUnits unit, int distance) {
        drivebaseSubsystem = subsystem;
        this.unit = unit;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.strafe(unit, distance);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
