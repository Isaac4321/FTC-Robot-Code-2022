package org.firstinspires.ftc.teamcode.internal.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;

public class LightOnCommand extends CommandBase {

    private final LightSubsystem lightSubsystem;

    private final LightSubsystem.LightType lightType;

    public LightOnCommand(LightSubsystem subsystem, LightSubsystem.LightType lightType) {
        lightSubsystem = subsystem;
        this.lightType = lightType;
        addRequirements(lightSubsystem);
    }

    @Override
    public void initialize() {
        lightSubsystem.on(lightType);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
