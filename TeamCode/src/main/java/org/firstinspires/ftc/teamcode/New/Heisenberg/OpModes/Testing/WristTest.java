package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;


@Config
@TeleOp(name = "RotatorTest", group = "Linear OpMode")
public class WristTest extends LinearOpMode {

    public static double angle = 0.0;

    @Override
    public void runOpMode() {
        ClawRotater wrist = new ClawRotater(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            wrist.update(gamepad1.left_stick_y * Math.PI);
        }
    }
}

