package org.firstinspires.ftc.teamcode.internal.util.functionality.driving;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class XDrivebase extends Drivebase {
    private boolean isScaled;

    public XDrivebase(DcMotor m1Front, DcMotor m2Front, DcMotor m3Back, DcMotor m4Left, boolean isScaled) {
        super(m1Front, m2Front, m3Back, m4Left);
        this.isScaled = isScaled;
    }

    public void drive(double forward, double turn) {
        if (isScaled) {
            motors[0].setPower(Math.pow(turn, 3));
            motors[1].setPower(Math.pow(forward, 3));
            motors[2].setPower(Math.pow(-turn, 3));
            motors[3].setPower(Math.pow(-forward, 3));
        }
        else {
            motors[0].setPower(turn);
            motors[1].setPower(forward);
            motors[2].setPower(-turn);
            motors[3].setPower(-forward);
        }
    }

    public void setScaled(boolean scaled) {
        isScaled = scaled;
    }

    public boolean isScaled() {
        return isScaled;
    }
}
