package org.firstinspires.ftc.teamcode.auto.commands;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

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
