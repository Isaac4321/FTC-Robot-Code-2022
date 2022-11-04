package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;

public class LightOffCommand extends CommandBase {

    private final LightSubsystem lightSubsystem;

    private final LightSubsystem.LightType lightType;

    public LightOffCommand(LightSubsystem subsystem, LightSubsystem.LightType lightType) {
        lightSubsystem = subsystem;
        this.lightType = lightType;
        addRequirements(lightSubsystem);
    }

    @Override
    public void initialize() {
        lightSubsystem.off(lightType);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
