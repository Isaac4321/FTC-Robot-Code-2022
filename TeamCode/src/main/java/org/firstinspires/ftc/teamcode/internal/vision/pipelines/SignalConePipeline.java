package org.firstinspires.ftc.teamcode.internal.vision.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class SignalConePipeline extends OpenCvPipeline {
    public enum SignalConeColour {
        RED(new Scalar(170, 100, 100), new Scalar(180, 255, 255)),
        GREEN(new Scalar(50, 100, 100), new Scalar(60, 255, 255)),
        BLUE(new Scalar(105, 100, 100), new Scalar(115, 255, 255)),
        UNDEFINED(null, null);

        private final Scalar[] colourBounds;

        SignalConeColour(Scalar lowerBound, Scalar upperBound) {
            colourBounds = new Scalar[]{lowerBound, upperBound};
        }

        public Scalar getLowerBound() {
            return colourBounds[0];
        }

        public Scalar getUpperBound() {
            return colourBounds[1];
        }
    }

    private SignalConeColour coneColour;

    private final Mat redMatrix = new Mat();
    private final Mat greenMatrix = new Mat();
    private final Mat blueMatrix = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Mat copyMatrix = new Mat();
        Imgproc.cvtColor(input, copyMatrix, Imgproc.COLOR_RGB2HSV);

        if (copyMatrix.empty()) {
            setSignalColour(0, 0, 0);
            return input;
        }

        Core.inRange(copyMatrix, SignalConeColour.RED.getLowerBound(), SignalConeColour.RED.getUpperBound(), redMatrix);
        Core.inRange(copyMatrix, SignalConeColour.GREEN.getLowerBound(), SignalConeColour.GREEN.getUpperBound(), greenMatrix);
        Core.inRange(copyMatrix, SignalConeColour.BLUE.getLowerBound(), SignalConeColour.BLUE.getUpperBound(), blueMatrix);

        double red = Core.mean(redMatrix).val[0];
        double green = Core.mean(greenMatrix).val[0];
        double blue = Core.mean(blueMatrix).val[0];

        setSignalColour(red, green, blue);

        return copyMatrix;
    }

    private void setSignalColour(double red, double green, double blue) {
        if (red > green && red > blue) {
            coneColour = SignalConeColour.RED;
        }
        else if (green > red && green > blue) {
            coneColour = SignalConeColour.GREEN;
        }
        else if (blue > green && blue > red) {
            coneColour = SignalConeColour.BLUE;
        }
        else {
            coneColour = SignalConeColour.UNDEFINED;
        }
    }

    public SignalConeColour getConeColour() {
        return coneColour;
    }

    public double[] getMatrices() {
        return new double[]{Core.mean(redMatrix).val[0], Core.mean(greenMatrix).val[0], Core.mean(blueMatrix).val[0]};
    }
}
