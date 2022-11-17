package org.firstinspires.ftc.teamcode.internal.auto.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

public class DriveCommand extends CommandBase {

    private DrivebaseSubsystem drivebaseSubsystem;

    private DrivebaseSubsystem.DistanceUnits unit;
    private int distance;
    private double heading;

    public DriveCommand(DrivebaseSubsystem subsystem, DrivebaseSubsystem.DistanceUnits unit, int distance, double heading) {
        drivebaseSubsystem = subsystem;
        this.unit = unit;
        this.distance = distance;
        this.heading = heading;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void initialize() {
        drivebaseSubsystem.drive(unit, distance, heading);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}