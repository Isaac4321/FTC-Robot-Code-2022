package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.opmodes.MainOpMode;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;

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

    /**
     * Called repeatedly during {@link MainOpMode#opModeIsActive()} assuming the command is scheduled.
     * Calls the {@link DrivebaseSubsystem#drive(double, double, double)}
     */
    @Override
    public void execute() {
        drivebaseSubsystem.drive(
                0.7 * drive.getAsDouble() * Math.abs(drive.getAsDouble()),
                0.7 * turn.getAsDouble() * Math.abs(turn.getAsDouble()),
                0.7 * rx.getAsDouble() * Math.abs(rx.getAsDouble()));
    }
}
