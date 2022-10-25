package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawSubsystem extends SubsystemBase {

    private final Servo claw;
    private final Motor motor = new Motor();
    public ClawSubsystem(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
    }

    public void grabCone() {
        claw.setPosition(0.56);
    }

    public void releaseCone() {
        claw.setPosition(0);
    }

}
