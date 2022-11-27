package org.firstinspires.ftc.teamcode.internal.util.functionality.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SingleServoClaw {
    private Servo servo;

    private double openPosition = 0.0;
    private double closedPosition = 0.0;

    public SingleServoClaw(HardwareMap hardwareMap, double openPosition, double closedPosition) {
        servo = hardwareMap.get(Servo.class, "clawServo");

        this.openPosition = openPosition;
        this.closedPosition = closedPosition;
    }

    public void closeClaw() {
        servo.setPosition(closedPosition);
    }

    public void openClaw() {
        servo.setPosition(openPosition);
    }

    public void setOpenPosition(double openPosition) {
        this.openPosition = openPosition;
    }

    public void setClosedPosition(double closedPosition) {
        this.closedPosition = closedPosition;
    }
}
