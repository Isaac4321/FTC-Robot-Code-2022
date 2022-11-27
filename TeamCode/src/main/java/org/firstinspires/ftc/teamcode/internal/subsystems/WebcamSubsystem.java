package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.internal.vision.pipelines.AprilTagDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class WebcamSubsystem extends SubsystemBase {
    private OpenCvWebcam webcam;
    private final AprilTagDetectionPipeline pipeline;
    private final Telemetry telemetry;

    private boolean isStreaming;

    private final int WIDTH = 640;
    private final int HEIGHT = 360;
    private final OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;

    private final double fx = 578.272;
    private final double fy = 578.272;
    private final double cx = 402.145;
    private final double cy = 221.506;

    //Meters
    private final double TAG_SIZE = 0.116;

    public WebcamSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        pipeline = new AprilTagDetectionPipeline(TAG_SIZE, fx, fy, cx, cy);

        webcam.setPipeline(pipeline);

        this.telemetry = telemetry;
    }

    public void startStreaming() {
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(WIDTH, HEIGHT, ROTATION);
                isStreaming = true;

                telemetry.addLine("Webcam is streaming");
                telemetry.update();
            }

            @Override
            public void onError(int errorCode) {
                isStreaming = false;

                telemetry.addLine("Error occurred during the opening of the webcam, error code: " + errorCode);
                telemetry.update();
            }
        });
    }

    public AprilTagDetectionPipeline getPipeline() {
        return pipeline;
    }

    public boolean isStreaming() {
        return isStreaming;
    }
}
