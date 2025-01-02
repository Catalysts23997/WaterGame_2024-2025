package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;


//Done by Noah D
//todo Review(commented)
//I used BasicOpMode_Linear as an example


@TeleOp(name = "Claw Rotator Test", group = "Linear OpMode")
@Disabled
public class ClawRotator extends LinearOpMode {
    public static double servoPos = 0.5;
    private ElapsedTime runtime = new ElapsedTime();
    private ClawRotater clawRotator = null;

    @Override
    public void runOpMode() throws InterruptedException {
        clawRotator = new ClawRotater(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            double drive = servoPos;


            clawRotator.update(drive);


            servoPos = ClawRotater.angle;
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //Not sure how format param works
            telemetry.addData("Claw Rotator", "", servoPos);
            telemetry.update();
        }
    }


}
