package org.firstinspires.ftc.teamcode.internal;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

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

    private static Robot robotInstance = null;
    private final OpMode opMode;

    private GamepadEx controller1 = null;
    private GamepadEx controller2 = null;

    private DrivebaseSubsystem drivebaseSubsystem;
    private LinkageSubsystem linkageSubsystem;
    private ClawSubsystem clawSubsystem;
//    private LightSubsystem lightSubsystem;

    boolean lightOn = false;
    boolean clawClosed = false;
    boolean isLifting = false;

    private final ArrayList<SubsystemBase> subsystems = new ArrayList<>();

    public enum OpModeType {
        TELE_OP, AUTO_OP
    }

    public Robot(OpMode opMode) {
        super();
        this.opMode = opMode;
        init();
    }

    private void init() {
        initSubsystems();
        initJoysticks();
    }

    private void initJoysticks() {
        controller1 = new GamepadEx(opMode.gamepad1);

        controller1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .toggleWhenPressed(new OpenClawCommand(clawSubsystem), new CloseClawCommand(clawSubsystem));

        controller1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new LiftLinkageCommand(linkageSubsystem))
                .whenReleased(new InstantCommand( () -> linkageSubsystem.stop()) );

        controller1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new DropLinkageCommand(linkageSubsystem))
                .whenReleased(new InstantCommand( () -> linkageSubsystem.stop()));

        controller1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new NextLinkagePositionCommand(linkageSubsystem))
                .whenReleased(new InstantCommand(() -> {
                    opMode.telemetry.addData("Current Position: " + linkageSubsystem.getCurrentPosition(), null);
                    opMode.telemetry.update();
                }));

        controller1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new PrevLinkagePositionCommand(linkageSubsystem))
                .whenReleased(new InstantCommand(() -> {
                    opMode.telemetry.addData("Current Position: " + linkageSubsystem.getCurrentPosition(), null);
                    opMode.telemetry.update();
                }));

//        controller1.getGamepadButton(GamepadKeys.Button.X)
//                        .toggleWhenPressed(
//                                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW),
//                                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW));

        drivebaseSubsystem.setDefaultCommand(new MecanumDriveCommand(drivebaseSubsystem,
                () -> controller1.getLeftX(),
                () -> controller1.getLeftY(),
                () -> controller1.getRightX()));
    }

//    public void run() {
//        new MecanumDriveCommand(drivebaseSubsystem,
//                controller1.left_stick_x,
//                -controller1.left_stick_y,
//                controller1.right_stick_x).execute();
//
//        if (controller1.x) {
//            if (lightOn) {
//                new LightOffCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW).execute();
//                lightOn = false;
//            }
//            else {
//                new LightOnCommand(lightSubsystem, LightSubsystem.LightType.UNDER_GLOW).execute();
//                lightOn = true;
//            }
//            while (controller1.x);
//        }
//
//        if (controller1.dpad_up) {
//            new NextLinkagePositionCommand(linkageSubsystem).execute();
//            new StopLinkageCommand(linkageSubsystem).execute();
//        }
//        if (controller1.dpad_down) {
//            new PrevLinkagePositionCommand(linkageSubsystem).execute();
//            new StopLinkageCommand(linkageSubsystem).execute();
//        }
//
//        if (controller1.a) {
//            new LiftLinkageCommand(linkageSubsystem).execute();
//            while (controller1.a) {
//
//            }
//            new StopLinkageCommand(linkageSubsystem).execute();
//        }
//
//        if (controller1.b) {
//            new DropLinkageCommand(linkageSubsystem).execute();
//            while (controller1.b) {
//
//            }
//            new StopLinkageCommand(linkageSubsystem).execute();
//        }
//
//        if (controller1.right_bumper) {
//            if (clawClosed) {
//                new OpenClawCommand(clawSubsystem).execute();
//                clawClosed = false;
//            }
//            else {
//                new CloseClawCommand(clawSubsystem).execute();
//                clawClosed = true;
//            }
//            while (controller1.right_bumper);
//        }
//    }

    private void initSubsystems() {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap);
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap);
//        lightSubsystem = new LightSubsystem(opMode.hardwareMap);

        register(drivebaseSubsystem, linkageSubsystem, clawSubsystem /*lightSubsystem*/);
    }

    /**
     * @return the robot's drivebase-subsystem
     */
    public DrivebaseSubsystem getDrivebaseSubsystem() {
        return drivebaseSubsystem;
    }

    /**
     * @return the robot's linkage-subsystem
     */
    public LinkageSubsystem getLinkageSubsystem() {
        return linkageSubsystem;
    }

    /**
     * @return the robot's claw-subsystem
     */
    public ClawSubsystem getClawSubsystem() {
        return clawSubsystem;
    }

    /**
     * @return the robot's light-subsystem
     */
//    public LightSubsystem getLightSubsystem() {
//        return lightSubsystem;
//    }
}
