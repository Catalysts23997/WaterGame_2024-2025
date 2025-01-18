package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

//todo add slide converter
@Config
@TeleOp(name = "TestConversionToInches", group = "Linear OpMode")
public class SlideConverter extends LinearOpMode {
    public static double motorpose=0.0;
    private DcMotor Leftslide;
    private DcMotor Rightslide;
    
    @Override
    public void runOpMode() throws InterruptedException {
//        hardwareMap.logDevices();
        DcMotor leftslide = hardwareMap.get(DcMotorEx.class, "leftslide");
        DcMotor rightslide = hardwareMap.get(DcMotorEx.class, "rightslide");
        rightslide.getCurrentPosition();
        leftslide.getCurrentPosition();

        waitForStart();
        while (opModeIsActive()){
            double leftpos = leftslide.getCurrentPosition();
            double rightpos = rightslide.getCurrentPosition();
            telemetry.addData("LeftPosition",leftpos);
            telemetry.addData("RightPosition",rightpos);

        }

    }
}