package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Attachments;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrist;

@Config
@TeleOp(name = "WristTest", group = "Linear OpMode")
public class WristTest extends LinearOpMode {

    public static double servoPose = 0.0;

    @Override
    public void runOpMode() {
        Wrist wrist = new Wrist(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            wrist.Wrist.setPosition(servoPose);
        }
    }
}

