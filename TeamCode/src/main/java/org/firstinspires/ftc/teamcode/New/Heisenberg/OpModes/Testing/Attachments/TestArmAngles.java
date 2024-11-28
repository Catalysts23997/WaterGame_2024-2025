package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Attachments;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.New.PIDTuner.Constants;

import ArmSpecific.ArmAngle;

@TeleOp(name = "TestArmAngles", group = "Linear OpMode")
public class TestArmAngles extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        Constants constants = new Constants();

        // Code executed when initialized
        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, constants.motorName);
        motor.setDirection(constants.motorDirection);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0.0);

        ArmAngle armAngle = new ArmAngle(constants.motor,Math.toRadians(3.0));
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Angle", Math.toDegrees(armAngle.findAngle(motor.getCurrentPosition())));
            telemetry.update();
        }
    }
}
//TODO HAVE accuracy be relative to robot voltage