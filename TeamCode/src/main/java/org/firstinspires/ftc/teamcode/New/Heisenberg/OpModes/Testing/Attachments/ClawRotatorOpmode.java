package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Attachments;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;

@Config
@TeleOp(name = "ClawRotaterTest", group = "Linear OpMode")
public class ClawRotatorOpmode extends LinearOpMode {
    public static double servoPose=0.0;
    private Gamepad gamepad1;
    @Override
    public void runOpMode() throws InterruptedException {
        ClawRotater clawRotater = new ClawRotater(hardwareMap);
        
        waitForStart();
        while (opModeIsActive()){
            clawRotater.clawRotater.setPosition(servoPose);
            if(gamepad1.a){
                clawRotater.state = ClawRotater.State.ADJUSTING;
            }
         if(gamepad1.x){
            clawRotater.state = ClawRotater.State.INPUT;}

         if (gamepad1.b){
            clawRotater.state = ClawRotater.State.ZERO;}
            if (gamepad1.y){
                clawRotater.state = ClawRotater.State.HalfWay;}
            telemetry.addData("ButtonValues=", "A= Adjusting, X= Input, B= Zero, Y= Halfway");

        }

    }
}

