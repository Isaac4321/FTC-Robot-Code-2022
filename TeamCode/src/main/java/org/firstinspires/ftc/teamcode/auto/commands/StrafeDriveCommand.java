package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

public class StrafeDriveCommand extends AutonomousCommand {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final int distance;
    private final boolean left;

    public StrafeDriveCommand(DrivebaseSubsystem subsystem, int distance, boolean left) {
        drivebaseSubsystem = subsystem;
        this.distance = distance;
        this.left = left;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.strafeDrive(distance, left);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
