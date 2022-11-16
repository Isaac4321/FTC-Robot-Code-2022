package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LightSubsystem extends SubsystemBase {

    private final Motor underGlow;
    private final Motor armGlow;

    private final double underGlowBrightness = 1;
    private final double armGlowBrightness = 1;

    public enum LightType {
        UNDER_GLOW,
        ARM_GLOW
    }

    public LightSubsystem(HardwareMap hardwareMap) {
        underGlow = new Motor(hardwareMap, "underGlow");
        armGlow = new Motor(hardwareMap, "armGlow");
    }

    public void on(LightType lightType) {
        if (lightType.equals(LightType.UNDER_GLOW)) {
            underGlow.set(underGlowBrightness);
        }
        else {
            armGlow.set(armGlowBrightness);
        }
    }

    public void off(LightType lightType) {
        if (lightType.equals(LightType.UNDER_GLOW)) {
            underGlow.set(0);
        }
        else {
            armGlow.set(0);
        }
    }

}
