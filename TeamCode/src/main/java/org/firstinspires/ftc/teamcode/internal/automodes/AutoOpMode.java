package org.firstinspires.ftc.teamcode.internal.automodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.commands.CloseClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOnCommand;
import org.firstinspires.ftc.teamcode.internal.commands.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="Auto Mode", group="Command Opmode")
public class AutoOpMode extends CommandOpMode {
    private Robot robot;
    private OpenCvWebcam webcam;

    enum SignalColours {
        PINK,
        YELLOW,
        GREEN
    }

    private SignalColours colour = SignalColours.PINK
            ;

    @Override
    public void runOpMode() {
        robot = new Robot(this);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(new SignalConePipeline());

        waitForStart();
        if (opModeIsActive()) {
            initialize();
        }
    }

    @Override
    public void initialize() {
//        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                webcam.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//
//            }
//        });

        if (colour == null) {
            schedule(new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 28));
        }
        else if (colour == SignalColours.PINK) {
            schedule(new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 25),
                     new StrafeDriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, -25));
        }
        else if (colour == SignalColours.GREEN) {
            schedule(new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 28));
        }
        else {
            schedule(new DriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 25),
                     new StrafeDriveCommand(robot.getDrivebaseSubsystem(), DrivebaseSubsystem.DistanceUnits.INCHES, 25));
        }

    }
    private class SignalConePipeline extends OpenCvPipeline {
        private final Mat mainMatrix = new Mat();

        private final Mat pinkMatrix = new Mat();
        private final Mat yellowMatrix = new Mat();
        private final Mat greenMatrix = new Mat();

        Scalar lowerPink = new Scalar(0.0, 150.0, 120.0);
        Scalar upperPink = new Scalar(255.0, 255.0, 255.0);

        Scalar lowerYellow = new Scalar(0.0, 100.0, 0.0);
        Scalar upperYellow = new Scalar(255.0, 170.0, 120.0);

        Scalar lowerGreen = new Scalar(  0.0, 0.0, 0.0);
        Scalar upperGreen = new Scalar(255.0, 120.0, 120.0);


        @Override
        public Mat processFrame(Mat input) {
            Imgproc.cvtColor(input, mainMatrix, Imgproc.COLOR_RGB2YCrCb);
            Core.inRange(mainMatrix, lowerPink, upperPink, pinkMatrix);
            Core.inRange(mainMatrix, lowerYellow, upperYellow, yellowMatrix);
            Core.inRange(mainMatrix, lowerGreen, upperGreen, greenMatrix);

            double avgPink = Core.mean(pinkMatrix).val[0];
            double avgYellow = Core.mean(yellowMatrix).val[0];
            double avgGreen = Core.mean(greenMatrix).val[0];

            if (avgPink > avgYellow && avgPink > avgGreen) {
                colour = SignalColours.PINK;
                return pinkMatrix;
            }
            if (avgYellow > avgPink && avgYellow > avgGreen) {
                colour = SignalColours.YELLOW;
                return yellowMatrix;
            }
            if (avgGreen > avgPink && avgGreen > avgYellow) {
                colour = SignalColours.GREEN;
                return greenMatrix;
            }

            return mainMatrix;
        }
    }
}
