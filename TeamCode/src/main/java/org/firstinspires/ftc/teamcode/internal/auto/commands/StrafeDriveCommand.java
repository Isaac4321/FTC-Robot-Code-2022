package org.firstinspires.ftc.teamcode.internal.auto.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class StrafeDriveCommand extends CommandBase {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final DrivebaseSubsystem.DistanceUnits unit;
    private final int distance;
    private final boolean left;

    public StrafeDriveCommand(DrivebaseSubsystem subsystem, DrivebaseSubsystem.DistanceUnits unit, int distance, boolean left) {
        drivebaseSubsystem = subsystem;
        this.unit = unit;
        this.distance = distance;
        this.left = left;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.strafe(unit, distance, left);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
