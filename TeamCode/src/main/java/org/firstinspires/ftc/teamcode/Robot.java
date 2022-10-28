package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.CloseClawCommand;
import org.firstinspires.ftc.teamcode.commands.DropLinkageCommand;
import org.firstinspires.ftc.teamcode.commands.LiftLinkageCommand;
import org.firstinspires.ftc.teamcode.commands.OpenClawCommand;
import org.firstinspires.ftc.teamcode.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.commands.StopLinkageCommand;
import org.firstinspires.ftc.teamcode.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.LinkageSubsystem;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class Robot extends com.arcrobotics.ftclib.command.Robot {

    private static Robot robot_instance = null;
    private final OpMode opMode;

    private GamepadEx controller1 = null;
    private GamepadEx controller2 = null;

    private DrivebaseSubsystem drivebaseSubsystem;
    private LinkageSubsystem linkageSubsystem;
    private ClawSubsystem clawSubsystem;

    private final ArrayList<SubsystemBase> subsystems = new ArrayList<>();

    public enum OpModeType {
        TELE_OP, AUTO_OP
    }

    private Robot(OpModeType type, OpMode opMode) {
        this.opMode = opMode;
        if (type == OpModeType.TELE_OP) {
            init(false);
        }
        else {
            init(true);
        }
    }

    public static Robot getRobotInstance(OpModeType type, OpMode opMode) {
        if (robot_instance == null) {
            robot_instance = new Robot(type, opMode);
        }
        return robot_instance;
    }

    private void init(boolean auto) {
        if (auto) {
            initSubsystems(true);
        }
        else {
            initSubsystems(false);
            initJoysticks();

        }

    }

    private void initJoysticks() {
        controller1 = new GamepadEx(opMode.gamepad1);
        controller2 = new GamepadEx(opMode.gamepad2);

        controller1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new OpenClawCommand(clawSubsystem))
                .whenReleased(new CloseClawCommand(clawSubsystem));

        drivebaseSubsystem.setDefaultCommand(new MecanumDriveCommand(drivebaseSubsystem,
                () -> controller1.getLeftX(),
                () -> controller1.getLeftY(),
                () -> controller1.getRightX()));
        controller1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new LiftLinkageCommand(linkageSubsystem))
                .whenReleased(new StopLinkageCommand(linkageSubsystem));

        controller1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new DropLinkageCommand(linkageSubsystem))
                .whenReleased(new StopLinkageCommand(linkageSubsystem));
    }

    private void initSubsystems(boolean auto) {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, auto);
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, auto);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, auto);

        subsystems.addAll(Arrays.asList(drivebaseSubsystem, linkageSubsystem, clawSubsystem));

        register(drivebaseSubsystem, linkageSubsystem, clawSubsystem);

    }

    public DrivebaseSubsystem getDrivebaseSubsystem() {
        return drivebaseSubsystem;
    }

    public LinkageSubsystem getLinkageSubsystem() {
        return linkageSubsystem;
    }

    public ClawSubsystem getClawSubsystem() {
        return clawSubsystem;
    }

    public ArrayList<SubsystemBase> getSubsystems() {
        return subsystems;
    }
}
