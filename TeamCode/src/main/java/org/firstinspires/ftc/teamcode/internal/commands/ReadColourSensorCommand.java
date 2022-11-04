package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LightSensorSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;

public class ReadColourSensorCommand extends CommandBase {

    private final LightSensorSubsystem lightSensorSubsystem;

    public ReadColourSensorCommand(LightSensorSubsystem subsystem) {
        lightSensorSubsystem = subsystem;
        addRequirements(lightSensorSubsystem);
    }

    @Override
    public void initialize() {
        lightSensorSubsystem.read();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
