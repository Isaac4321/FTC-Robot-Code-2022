package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ClawSubsystem extends SubsystemBase {

    private final ServoEx claw;

    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = new SimpleServo(hardwareMap, "clawServo", 0, 270, AngleUnit.DEGREES);
    }

    public void grabCone() {
        claw.rotateByAngle(90);
    }

    public void releaseCone() {
        claw.rotateByAngle(-90);
    }

}
