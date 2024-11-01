package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.Localizer;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.GoBildaPinpointDriver;

@TeleOp(name = "Pinpoint", group = "Linear OpMode")
public class Pinpoint extends LinearOpMode {

    @Override
    public void runOpMode() {
        GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "odo");

        pinpoint.setOffsets(-84.0, -168.0);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.REVERSED
        );

        pinpoint.resetPosAndIMU();
        waitForStart();
        while (opModeIsActive()) {
            pinpoint.update();
            telemetry.addData("X", pinpoint.getPosX());
            telemetry.addData("Y", pinpoint.getPosY());
            telemetry.addData("H", Math.toDegrees(pinpoint.getHeading()));
            telemetry.update();
        }


    }
}

