package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.Bromine;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;
@Config
@TeleOp(name = "WristTest", group = "Linear OpMode")
public class WristTest extends LinearOpMode {

    public static double servoPose=0.0;

    @Override
    public void runOpMode() {
        Bromine bromine = new Bromine(hardwareMap);

        waitForStart();
        while (opModeIsActive()){
            bromine.wrist.Wrist.setPosition(servoPose);
        }


    }
}

