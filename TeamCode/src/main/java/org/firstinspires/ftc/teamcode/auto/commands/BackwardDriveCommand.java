package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

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
