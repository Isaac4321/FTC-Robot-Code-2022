package org.firstinspires.ftc.teamcode.auto.routes;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.command.SubsystemBase;

import java.util.List;

public class AdvancedRoute extends SequentialCommandGroup {

    public AdvancedRoute(List<SubsystemBase> subsystems, CommandBase... commands) {
        addCommands(commands);
        addRequirements((Subsystem) subsystems);
    }
}
