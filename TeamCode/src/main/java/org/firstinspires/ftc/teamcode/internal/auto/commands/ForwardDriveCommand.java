package org.firstinspires.ftc.teamcode.internal.auto.commands;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class ForwardDriveCommand extends AutonomousCommand {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final int distance;

    public ForwardDriveCommand(DrivebaseSubsystem subsystem, int distance) {
        drivebaseSubsystem = subsystem;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.forwardDrive(distance);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
