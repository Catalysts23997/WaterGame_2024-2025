package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.PIDParams;

import CommonUtilities.PIDFcontroller;

@Config
@TeleOp(name = "LinerSlidesTesting", group = "Linear OpMode")
public class Tuning extends LinearOpMode{
        public static double motorpose=0.0;
        private DcMotorEx Leftslide;
        private DcMotorEx Rightslide;
        double p = 0.0;
        double i = 0.0;
        double d = 0.0;
        double isum = 0.0;
        // these are what u change. THESE are the constants you change.
        double iGain = 0.0;
        double dGain = 0.0;
        double pGain = 0.0;
        double finalPID = 0.0;

        public double lastError = 0;
        double error = 0;

        DcMotor leftslide = hardwareMap.get(DcMotorEx.class, "Leftslide");
         DcMotor rightslide = hardwareMap.get(DcMotorEx.class, "Rightslide");
    //don't know why this is having errors??? I tried a lot of stuff but I think it's smth with the setup.
//        Leftslide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        Rightslide.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
//        leftslide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        rightslide.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);


//        PIDFcontroller pidFcontroller = new (new PIDParams(p,i,d,0.0));

        @Override
        public void runOpMode() throws InterruptedException {
         LinearSlides slides = new LinearSlides(hardwareMap);

            waitForStart();
            while (opModeIsActive()){

                lastError= error;
                error = ((leftslide.getTargetPosition()- leftslide.getCurrentPosition()) + (rightslide.getTargetPosition()-rightslide.getCurrentPosition()));

                 //p-- multiplies the coefficient by the error to react to it and get a motor speed :) P is the final output
                p = pGain * error;

                //i--- honestly I'm not too sure if this is necessary or not for this project. but it multiplies a constant by the total accumulated error of the slides.
                isum += error;
                i = (iGain * isum);

                //d multiplies a constant by the difference between the last two errors to make sure there's no abnormalities
                d = (dGain * (error - lastError));

                finalPID = p + i + d;



               telemetry.addData("p", p);
               telemetry.addData("i", i);
               telemetry.addData("d",d);
               telemetry.addData("PID",finalPID);
               leftslide.setPower(finalPID);
               rightslide.setPower(finalPID);

            }

        }
    }

