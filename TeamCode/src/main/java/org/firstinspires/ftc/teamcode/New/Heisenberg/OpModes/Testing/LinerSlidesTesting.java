package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Config
@TeleOp(name = "LinerSlidesTesting", group = "Linear OpMode")
public class LinerSlidesTesting extends LinearOpMode {
    public static double motorpose=0.0;
    DcMotor leftSlide;
    DcMotor rightslide;


    @Override
    public void runOpMode() throws InterruptedException {
        rightslide.getCurrentPosition();
        leftSlide.getCurrentPosition();
        double leftpos = leftSlide.getCurrentPosition();
        double rightpos = rightslide.getCurrentPosition();
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("LeftPosition",leftpos);
            telemetry.addData("RightPosition",rightpos);

        }

    }
}
