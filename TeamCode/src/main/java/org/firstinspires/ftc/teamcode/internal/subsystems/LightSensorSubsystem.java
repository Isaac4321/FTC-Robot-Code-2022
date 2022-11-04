package org.firstinspires.ftc.teamcode.internal.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.checkerframework.checker.units.qual.C;

public class LightSensorSubsystem extends CustomSubsystemBase{
    private final ColorSensor colorSensor;

    enum ColourCodes {
        MAGENTA,
        CYAN,
        YELLOW,
        UNDEFINED
    }

    private ColourCodes color = ColourCodes.UNDEFINED;

    public LightSensorSubsystem(HardwareMap hardwareMap, boolean auto) {
        super(hardwareMap, auto);
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        colorSensor.enableLed(false);
    }

    public void read(){
        colorSensor.enableLed(true);
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();

        if((red < 255 && red > 200) && (blue < 255 && blue > 200)) {
            color = ColourCodes.MAGENTA;
        }
        else if((blue < 255 && blue < 200) && (green < 255 && green > 200)) {
            color = ColourCodes.CYAN;
        }
        else if((red < 255 && red > 200) && (green < 255 && green > 200)) {
            color = ColourCodes.YELLOW;
        }
        else {
            color = ColourCodes.UNDEFINED;
        }
    }


    public String getColor() {
        return "Red: " + colorSensor.red() + "\nGreen: " + colorSensor.green() + "\nBlue: " + colorSensor.blue();
    }
}
