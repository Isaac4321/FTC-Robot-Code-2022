package org.firstinspires.ftc.teamcode.internal.automodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.commands.FunctionalCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.util.AprilTagConstants;
import org.openftc.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.Collections;

@Autonomous(name="Auto: Blue Alliance Right")
public class AdvancedAutonomousRightBlue extends LinearOpMode {
    private Robot robot;

    private boolean tagFound = false;
    private int parkingZone;

    private final ArrayList<FunctionalCommand> drivenCommands = new ArrayList<>();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        robot.initWebcamSubsystem();
        robot.getWebcamSubsystem().startStreaming();

        while (!isStarted() && !isStopRequested()) {
            detectTag();
            if (tagFound) {
                break;
            }
        }

        if (opModeIsActive()) {
            if (tagFound) {
                deploy();
                wait(1000);

                driveToPlaceCone();
                wait(1000);

                goBackToHome();
                wait(1000);

                driveToParking();
            }
            else {
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
            }
        }
    }

    private void detectTag() {
        ArrayList<AprilTagDetection> detections = robot.getWebcamSubsystem().getPipeline().getLatestDetections();

        if (detections.size() != 0 ) {
            for (AprilTagDetection tag : detections) {
                if (AprilTagConstants.parkingZone1Tags.contains(tag.id)) {
                    parkingZone = 1;
                    tagFound = true;
                }
                if (AprilTagConstants.parkingZone2Tags.contains(tag.id)) {
                    parkingZone = 2;
                    tagFound = true;
                }
                if (AprilTagConstants.parkingZone3Tags.contains(tag.id)) {
                    parkingZone = 3;
                    tagFound = true;
                }
            }
        }
    }

    private void driveToPlaceCone() {
        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -28);
        drivenCommands.add(() -> robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 28));

        robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28 * 3);
        drivenCommands.add(() -> robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, -28 * 3));

        robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, 14);
        drivenCommands.add(() -> robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -14));

        robot.getClawSubsystem().openClaw();
    }

    public void goBackToHome() {
        Collections.reverse(drivenCommands);
        for (FunctionalCommand command : drivenCommands) {
            command.execute();
        }
    }

    private void deploy() {
        robot.getClawSubsystem().openClaw();
        robot.getClawSubsystem().closeClaw();

        wait(2000);
        robot.getLinkageSubsystem().nextPos();
        robot.getLinkageSubsystem().nextPos();
    }

    private void driveToParking() {
        switch (parkingZone) {
            case 1:
                robot.getDrivebaseSubsystem().strafe(DrivebaseSubsystem.DistanceUnits.INCHES, -28);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
            case 2:
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
            case 3:
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                robot.getDrivebaseSubsystem().drive(DrivebaseSubsystem.DistanceUnits.INCHES, 28);
                break;
        }
    }

    private void wait(int milliseconds) {
        ElapsedTime time = new ElapsedTime();
        while (time.milliseconds() < milliseconds);
    }
}
