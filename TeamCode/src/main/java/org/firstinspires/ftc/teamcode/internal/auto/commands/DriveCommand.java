package org.firstinspires.ftc.teamcode.internal.auto.commands;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class DriveCommand extends AutonomousCommandBase {

    private final DrivebaseSubsystem drivebaseSubsystem;
    private final int distance;

    public DriveCommand(DrivebaseSubsystem subsystem, int distance) {
        drivebaseSubsystem = subsystem;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.drive(distance);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
