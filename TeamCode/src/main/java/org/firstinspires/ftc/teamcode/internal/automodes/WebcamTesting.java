package org.firstinspires.ftc.teamcode.internal.automodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.internal.Robot;
import org.firstinspires.ftc.teamcode.internal.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.StrafeDriveCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.vision.pipelines.SignalConePipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name="Auto: Drive To Correct Parking")
public class WebcamTesting extends LinearOpMode {
    private Robot robot;
    private OpenCvWebcam webcam;
    private final SignalConePipeline signalConePipeline = new SignalConePipeline();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(this);

        waitForStart();
        if (opModeIsActive()) {

            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
            webcam.setPipeline(signalConePipeline);

            webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    webcam.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {

                }
            });

        }

    }

}
