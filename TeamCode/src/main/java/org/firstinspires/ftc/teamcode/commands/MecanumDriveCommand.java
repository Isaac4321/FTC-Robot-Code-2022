package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {

    private final DrivebaseSubsystem drivebaseSubsystem;

    private final DoubleSupplier drive;
    private final DoubleSupplier turn;
    private final DoubleSupplier rx;

    public MecanumDriveCommand(DrivebaseSubsystem subsystem, DoubleSupplier left_x,
                               DoubleSupplier left_y, DoubleSupplier right_x) {
        drivebaseSubsystem = subsystem;
        drive = left_x;
        turn = left_y;
        rx = right_x;
        addRequirements(drivebaseSubsystem);
    }

    @Override
    public void execute() {
        drivebaseSubsystem.drive(
                drive.getAsDouble(),
                turn.getAsDouble(),
                rx.getAsDouble());
    }



}
