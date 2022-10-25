package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinkageSubsystem extends SubsystemBase {

    private final double LIFT_SPEED_PERCENTAGE = 0.10;
    private final double DROP_SPEED_PERCENTAGE = 0.10;

    private final Motor leftMotor;
    private final Motor rightMotor;

    public enum TargetPosition {
        HOME, SMALLEST_POLE, MEDIUM_POLE, TALLEST_POLE
    }

    public LinkageSubsystem(HardwareMap hardwareMap) {
        leftMotor = new Motor(hardwareMap, "leftLinkageMotor", Motor.GoBILDA.RPM_60);
        rightMotor = new Motor(hardwareMap, "rightLinkageMotor", Motor.GoBILDA.RPM_60);

        leftMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void lift() {
        leftMotor.set(LIFT_SPEED_PERCENTAGE);
        rightMotor.set(LIFT_SPEED_PERCENTAGE);
    }

    public void drop() {
        leftMotor.set(-DROP_SPEED_PERCENTAGE);
        rightMotor.set(-DROP_SPEED_PERCENTAGE);
    }

    public void stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }

}
