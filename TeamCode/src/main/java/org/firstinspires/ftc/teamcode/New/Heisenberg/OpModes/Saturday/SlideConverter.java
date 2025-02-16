package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv;

//todo add slide converter
@Disabled
@Config
@TeleOp(name = "TestConversionToInches", group = "Linear OpMode")
public class SlideConverter extends LinearOpMode {
    public static double motorpose=0.0;
    private DcMotor Leftslide;
    private DcMotor Rightslide;
    
    @Override
    public void runOpMode() throws InterruptedException {
//        hardwareMap.logDevices();
        DcMotor slide = hardwareMap.get(DcMotorEx.class, "slide");
        slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        SlidesEncoderConv calc = new SlidesEncoderConv(24*Math.PI);

        waitForStart();
        while (opModeIsActive()){
            int leftpos = slide.getCurrentPosition();
            telemetry.addData("LeftPosition",leftpos);

            telemetry.addData("Inches",calc.toIn(leftpos));
            telemetry.update();
        }

    }
}