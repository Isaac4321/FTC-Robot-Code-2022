package org.firstinspires.ftc.teamcode.internal.auto.commands;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class BackwardDriveCommand extends AutonomousCommand {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final int distance;

    public BackwardDriveCommand(DrivebaseSubsystem subsystem, int distance) {
        drivebaseSubsystem = subsystem;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.backwardDrive(distance);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
