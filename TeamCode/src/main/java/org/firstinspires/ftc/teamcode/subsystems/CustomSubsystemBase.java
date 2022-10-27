package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class CustomSubsystemBase extends SubsystemBase {

    public boolean auto;
    public HardwareMap hardwareMap;

    public CustomSubsystemBase(HardwareMap hardwareMap, boolean auto) {
        this.auto = auto;
        this.hardwareMap = hardwareMap;
    }

    public boolean isAuto() {
        return auto;
    }

    public abstract String getTask();
}
