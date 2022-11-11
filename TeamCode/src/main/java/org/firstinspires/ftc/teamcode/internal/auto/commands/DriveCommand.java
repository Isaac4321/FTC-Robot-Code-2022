package org.firstinspires.ftc.teamcode.internal.auto.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class DriveCommand extends CommandBase {

    private DrivebaseSubsystem drivebaseSubsystem;
    private int distance;

    public DriveCommand(DrivebaseSubsystem subsystem, int distance) {
        drivebaseSubsystem = subsystem;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.driveT(distance);
    }

}
