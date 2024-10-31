package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.Bromine;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.WristJohn;

@TeleOp(name = "ArmTest", group = "Linear OpMode")
public class ArmTest extends LinearOpMode {

    Bromine bromine;


    @Override
    public void runOpMode() {

        waitForStart();
        while (opModeIsActive()){
            bromine.shoulder.state = ShoudlerJohn.State.IDLE;
            bromine.wrist.Wrist.setPosition(-gamepad1.left_stick_y);
            bromine.clawRotater.clawRotater.setPosition(-gamepad1.left_stick_x);

            bromine.update(0);

        }


    }
}

