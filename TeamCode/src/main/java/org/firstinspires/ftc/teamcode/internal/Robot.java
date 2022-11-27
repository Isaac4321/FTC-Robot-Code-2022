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
import org.firstinspires.ftc.teamcode.internal.subsystems.WebcamSubsystem;
import org.firstinspires.ftc.teamcode.internal.vision.pipelines.AprilTagDetectionPipeline;

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

    private WebcamSubsystem webcamSubsystem;

    private final ArrayList<SubsystemBase> subsystems = new ArrayList<>();


    public Robot(OpMode opMode) {
        this.opMode = opMode;
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

        drivebaseSubsystem.setDefaultCommand(new MecanumDriveCommand(drivebaseSubsystem,
                () -> controller1.getLeftX(),
                () -> controller1.getLeftY(),
                () -> controller1.getRightX()));
    }

    private void initSubsystems() {
        drivebaseSubsystem = new DrivebaseSubsystem(opMode.hardwareMap);
        linkageSubsystem = new LinkageSubsystem(opMode.hardwareMap);
        clawSubsystem = new ClawSubsystem(opMode.hardwareMap);

        register(drivebaseSubsystem, linkageSubsystem, clawSubsystem);
    }

    public void initWebcamSubsystem() {
        webcamSubsystem = new WebcamSubsystem(opMode.hardwareMap, opMode.telemetry);
    }

    public WebcamSubsystem getWebcamSubsystem() {
        return webcamSubsystem;
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
