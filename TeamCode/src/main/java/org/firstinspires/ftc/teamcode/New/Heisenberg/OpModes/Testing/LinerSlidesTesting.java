package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp(name = "LinerSlidesTesting", group = "Linear OpMode")
public class LinerSlidesTesting extends LinearOpMode {
    public static double motorpose=0.0;
//    DcMotor leftslide;
//    DcMotor rightslide;

    DcMotor leftslide = hardwareMap.get(DcMotorEx.class, "leftslide");
    DcMotor rightslide = hardwareMap.get(DcMotorEx.class, "rightslide");


    @Override
    public void runOpMode() throws InterruptedException {
        rightslide.getCurrentPosition();
        leftslide.getCurrentPosition();
        double leftpos = leftslide.getCurrentPosition();
        double rightpos = rightslide.getCurrentPosition();
        waitForStart();
        while (opModeIsActive()){
            telemetry.addData("LeftPosition",leftpos);
            telemetry.addData("RightPosition",rightpos);

        }

    }
}
