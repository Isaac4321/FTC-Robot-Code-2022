package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class DriveCommand extends CommandBase {

    private DrivebaseSubsystem drivebaseSubsystem;

    private DrivebaseSubsystem.DistanceUnits unit;
    private int distance;

    public DriveCommand(DrivebaseSubsystem subsystem, DrivebaseSubsystem.DistanceUnits unit, int distance) {
        drivebaseSubsystem = subsystem;
        this.unit = unit;
        this.distance = distance;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.drive(unit, distance);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
