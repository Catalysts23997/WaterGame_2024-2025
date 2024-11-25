package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Attachments;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;

@Config
@TeleOp(name = "ClawRotaterTest", group = "Linear OpMode")
public class ClawRotaterTest extends LinearOpMode {
    public static double servoPose=0.0;
    @Override
    public void runOpMode() {
        ClawRotater clawRotater = new ClawRotater(hardwareMap);

        waitForStart();
        while (opModeIsActive()){
            clawRotater.clawRotater.setPosition(servoPose);
        }


    }
}

