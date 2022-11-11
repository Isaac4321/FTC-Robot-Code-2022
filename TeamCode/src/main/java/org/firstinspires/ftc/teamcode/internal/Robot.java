package org.firstinspires.ftc.teamcode.internal;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.internal.auto.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.CloseClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.DropLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LiftLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOffCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOnCommand;
import org.firstinspires.ftc.teamcode.internal.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.NextLinkagePositionCommand;
import org.firstinspires.ftc.teamcode.internal.commands.OpenClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.PrevLinkagePositionCommand;
import org.firstinspires.ftc.teamcode.internal.commands.StopLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LightSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LinkageSubsystem;

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
    private LightSubsystem lightSubsystem;

    private final ArrayList<SubsystemBase> subsystems = new ArrayList<>();

    public enum OpModeType {
        TELE_OP, AUTO_OP
    }

    private Robot(OpModeType type, OpMode opMode) {
        this.opMode = opMode;
        init(type != OpModeType.TELE_OP);
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
//
        controller1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenPressed(new OpenClawCommand(clawSubsystem), new CloseClawCommand(clawSubsystem));
//
        controller2.getGamepadButton(GamepadKeys.Button.A)
                        .whenPressed(new DriveCommand(drivebaseSubsystem, 61));

        controller2.getGamepadButton(GamepadKeys.Button.B)
                        .whenPressed(new DriveCommand(drivebaseSubsystem, -61));

        controller1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new LiftLinkageCommand(linkageSubsystem))
                .whenReleased(new InstantCommand( () -> linkageSubsystem.stop()) );

        controller1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new DropLinkageCommand(linkageSubsystem))
                .whenReleased(new InstantCommand( () -> linkageSubsystem.stop()));
//
//        controller1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
//                .whenPressed(new NextLinkagePositionCommand(linkageSubsystem))
//                .whenReleased(new InstantCommand(() -> {
//                    opMode.telemetry.addData("Current Position: " + linkageSubsystem.getCurrentPosition(), null);
//                    opMode.telemetry.update();
//                }));
//
//        controller1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
//                .whenPressed(new PrevLinkagePositionCommand(linkageSubsystem))
//                .whenReleased(new InstantCommand(() -> {
//                    opMode.telemetry.addData("Current Position: " + linkageSubsystem.getCurrentPosition(), null);
//                    opMode.telemetry.update();
//                }));

        controller1.getGamepadButton(GamepadKeys.Button.X)
                        .toggleWhenPressed(
                                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW),
                                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW));
        controller1.getGamepadButton(GamepadKeys.Button.Y)
                        .toggleWhenPressed(
                                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.ARM_GLOW),
                                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.ARM_GLOW));

        drivebaseSubsystem.setDefaultCommand(new MecanumDriveCommand(drivebaseSubsystem,
                () -> controller1.getLeftX(),
                () -> controller1.getLeftY(),
                () -> controller1.getRightX()));
    }

    private void initSubsystems(boolean auto) {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap);
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, false);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, auto);
        lightSubsystem = new LightSubsystem(opMode.hardwareMap, auto);

        subsystems.addAll(Arrays.asList(drivebaseSubsystem, linkageSubsystem, clawSubsystem, lightSubsystem));

        register(drivebaseSubsystem, linkageSubsystem, clawSubsystem, lightSubsystem);

    }

//    public DrivebaseSubsystem getDrivebaseSubsystem() {
//        return drivebaseSubsystem;
//    }
//
//    public LinkageSubsystem getLinkageSubsystem() {
//        return linkageSubsystem;
//
//    }
//
//    public ClawSubsystem getClawSubsystem() {
//        return clawSubsystem;
//    }

    public ArrayList<SubsystemBase> getSubsystems() {
        return subsystems;
    }
}
