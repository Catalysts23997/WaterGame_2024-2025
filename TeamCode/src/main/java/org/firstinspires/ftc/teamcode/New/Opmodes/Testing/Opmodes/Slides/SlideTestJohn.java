//package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.Slides;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.New.Future.SubSystems.LinearSlides;
//@Disabled
//@TeleOp(name = "SlideTestJohn", group = "Linear OpMode")
//public class SlideTestJohn extends LinearOpMode {
//    LinearSlides verticalSlides = new LinearSlides(hardwareMap);
//    LinearSlides horizontalSlides = new LinearSlides(hardwareMap);
//
//    @Override
//    public void runOpMode() {
//
//        verticalSlides.resetValue = 0;
//        horizontalSlides.resetValue = 0;
//        update();
//        waitForStart();
//
//        while (opModeIsActive()){
//
//            update();
//            telemetry.addData("rightEncoder", verticalSlides.rightSlide.rightEncoder);
//            telemetry.addData("leftEncoder", verticalSlides.leftSlide.leftEncoder);
//            telemetry.update();
//
//        }
//    }
//
//    private void update(){
//        verticalSlides.update();
//        horizontalSlides.update();
//    }
//
//}
