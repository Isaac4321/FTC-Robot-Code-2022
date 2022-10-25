package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivebaseSubsystem extends SubsystemBase {

    private final MecanumDrive mecanum;

    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor rearLeft;
    private final Motor rearRight;

    public DrivebaseSubsystem(HardwareMap hardwareMap) {
        frontLeft = new Motor(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_60);
        frontRight = new Motor(hardwareMap, "frontRight", Motor.GoBILDA.RPM_60);
        rearLeft = new Motor(hardwareMap, "rearLeft", Motor.GoBILDA.RPM_60);
        rearRight = new Motor(hardwareMap, "rearRight", Motor.GoBILDA.RPM_60);

        setZeroPowerBehaviors(Motor.ZeroPowerBehavior.BRAKE, frontLeft, frontRight, rearLeft, rearRight);

        mecanum = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);
    }

    public void drive(double left_x, double left_y, double right_x) {
        mecanum.driveRobotCentric(left_x, left_y, right_x);
    }

    private void setZeroPowerBehaviors(Motor.ZeroPowerBehavior zeroPowerBehavior, Motor... motors) {
        for (Motor motor : motors) {
            motor.setZeroPowerBehavior(zeroPowerBehavior);
        }
    }
}

