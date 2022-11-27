package org.firstinspires.ftc.teamcode.internal.util.functionality.intake;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DualMotorIntake {
    private DcMotor motor1;
    private DcMotor motor2;

    private double intakeSpeed = 0.5;
    private double releaseSpeed = -0.5;

    public DualMotorIntake(DcMotor motor1, DcMotor motor2, boolean reversed) {
        this.motor1 = motor1;
        this.motor2 = motor2;
        if (reversed) {
            this.motor1.setDirection(reversed ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
            this.motor2.setDirection(reversed ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE);
        }
    }

    public void intake() {
        this.motor1.setPower(intakeSpeed);
        this.motor2.setPower(intakeSpeed);
    }

    public void release() {
        this.motor1.setPower(releaseSpeed);
        this.motor2.setPower(releaseSpeed);
    }
}
