/*
 * Copyright (c) 2019 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.internal.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class WebcamExample extends OpMode
{
    OpenCvWebcam webcam;

    @Override
    public void init() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(new SamplePipeline());

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

    @Override
    public void loop() {

    }

    class SamplePipeline extends OpenCvPipeline
    {
        Mat mat = new Mat();

        Mat pink = new Mat();
        Mat yellow = new Mat();
        Mat green = new Mat();

        Scalar lowerPink = new Scalar(0.0, 150.0, 120.0);
        Scalar upperPink = new Scalar(255.0, 255.0, 255.0);

        Scalar lowerYellow = new Scalar(0.0, 100.0, 0.0);
        Scalar upperYellow = new Scalar(255.0, 170.0, 120.0);

        Scalar lowerGreen = new Scalar(  0.0, 0.0, 0.0);
        Scalar upperGreen = new Scalar(255.0, 120.0, 120.0);

        @Override
        public Mat processFrame(Mat input)
        {
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2YCrCb);
            Core.inRange(mat, lowerPink, upperPink, pink);
            Core.inRange(mat, lowerYellow, upperYellow, yellow);
            Core.inRange(mat, lowerGreen, upperGreen, green);

            double avgPink = Core.mean(pink).val[0];
            double avgYellow = Core.mean(yellow).val[0];
            double avgGreen = Core.mean(green).val[0];


            telemetry.addLine("Pink: " + avgPink);
            telemetry.addLine("Yellow: " + avgYellow);
            telemetry.addLine("green: " + avgGreen);
            telemetry.update();
            return input;
        }
    }
}
