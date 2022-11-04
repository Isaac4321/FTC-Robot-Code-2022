package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Abstract wrapper class for Subsystems.
 *
 * @author Esquimalt Atom Smashers
 */
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

}
