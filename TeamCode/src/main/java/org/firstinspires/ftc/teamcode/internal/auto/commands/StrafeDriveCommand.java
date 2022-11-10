package org.firstinspires.ftc.teamcode.internal.auto.commands;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class StrafeDriveCommand extends AutonomousCommandBase {

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
        drivebaseSubsystem.strafe(distance, left);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
