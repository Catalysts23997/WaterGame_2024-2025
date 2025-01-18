package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;


@Disabled
@Config
@TeleOp(name = "ClawTest", group = "Linear OpMode")
public class ClawTest extends LinearOpMode {

    public static double servoPose=0.0;

    @Override
    public void runOpMode() {

        Claw claw = new Claw(hardwareMap);

        waitForStart();
        while (opModeIsActive()){
            claw.claw.setPosition(servoPose);
        }
    }
}

