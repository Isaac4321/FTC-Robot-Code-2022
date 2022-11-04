package org.firstinspires.ftc.teamcode.internal;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.internal.commands.CloseClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.DropLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LiftLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOffCommand;
import org.firstinspires.ftc.teamcode.internal.commands.LightOnCommand;
import org.firstinspires.ftc.teamcode.internal.commands.OpenClawCommand;
import org.firstinspires.ftc.teamcode.internal.commands.MecanumDriveCommand;
import org.firstinspires.ftc.teamcode.internal.commands.ReadColourSensorCommand;
import org.firstinspires.ftc.teamcode.internal.commands.StopLinkageCommand;
import org.firstinspires.ftc.teamcode.internal.subsystems.ClawSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.internal.subsystems.LightSensorSubsystem;
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

//    private DrivebaseSubsystem drivebaseSubsystem;
//    private LinkageSubsystem linkageSubsystem;
//    private ClawSubsystem clawSubsystem;
    private LightSubsystem lightSubsystem;
    private LightSensorSubsystem lightSensorSubsystem;

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
//            drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, false);
            initJoysticks();


        }
    }

    private void initJoysticks() {
        controller1 = new GamepadEx(opMode.gamepad1);
        controller2 = new GamepadEx(opMode.gamepad2);
//
//        controller1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
//                .toggleWhenPressed(new OpenClawCommand(clawSubsystem), new CloseClawCommand(clawSubsystem));
//
//        controller1.getGamepadButton(GamepadKeys.Button.A)
//                .whenPressed(new LiftLinkageCommand(linkageSubsystem))
//                .whenReleased(new StopLinkageCommand(linkageSubsystem));
//
//        controller1.getGamepadButton(GamepadKeys.Button.B)
//                .whenPressed(new DropLinkageCommand(linkageSubsystem))
//                .whenReleased(new StopLinkageCommand(linkageSubsystem));

        controller1.getGamepadButton(GamepadKeys.Button.X)
                        .toggleWhenPressed(
                                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW),
                                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW));
        controller1.getGamepadButton(GamepadKeys.Button.Y)
                        .toggleWhenPressed(
                                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.ARM_GLOW),
                                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.ARM_GLOW));

        controller1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ReadColourSensorCommand(lightSensorSubsystem));

        controller1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new InstantCommand(() -> {
                    opMode.telemetry.addData(String.valueOf(lightSensorSubsystem.getColor()), null);
                    opMode.telemetry.update();
                }));

//        drivebaseSubsystem.setDefaultCommand(new MecanumDriveCommand(drivebaseSubsystem,
//                () -> controller1.getLeftX(),
//                () -> controller1.getLeftY(),
//                () -> controller1.getRightX()));


    }

    private void initSubsystems(boolean auto) {
//        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap, auto);
//        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap, auto);
//        clawSubsystem = new ClawSubsystem(opMode.hardwareMap, auto);
        lightSubsystem = new LightSubsystem(opMode.hardwareMap, auto);
        lightSensorSubsystem = new LightSensorSubsystem(opMode.hardwareMap, auto);

        subsystems.addAll(Arrays.asList(/*drivebaseSubsystem, linkageSubsystem, clawSubsystem, */lightSubsystem));

        register(/*drivebaseSubsystem, linkageSubsystem, clawSubsystem, */lightSubsystem);

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


    public LightSensorSubsystem getLightSensorSubsystem() {
        return lightSensorSubsystem;
    }
    public ArrayList<SubsystemBase> getSubsystems() {
        return subsystems;
    }
}
