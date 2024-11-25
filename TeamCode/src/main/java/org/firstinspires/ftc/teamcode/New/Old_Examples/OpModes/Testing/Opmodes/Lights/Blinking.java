package org.firstinspires.ftc.teamcode.New.Old_Examples.OpModes.Testing.Opmodes.Lights;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name = "Blinking", group = "Linear OpMode")
public class Blinking extends LinearOpMode {
    RevBlinkinLedDriver leftLights,rightLights;
    @Override
    public void runOpMode() throws InterruptedException {
        leftLights = hardwareMap.get(RevBlinkinLedDriver.class, "leftLights");
        rightLights = hardwareMap.get(RevBlinkinLedDriver.class, "rightLights");

        waitForStart();
        resetRuntime();
        while (opModeIsActive()) {
            telemetry.addData(" Timer", time);
            telemetry.update();
            updateBlinkin();
        }
    }
    public void updateBlinkin() {

        if (time < 5) {
            blinkinRed();
        } else if ( time >= 5 && time < 10) {
            blinkinGreen();
        } else if (time >= 10 && time < 15) {
            blinkinBlue();
        } else if (time >= 15 && time < 20) {
            blinkinOrange();
        } else {
            blinkinBlack();
        }
    }

    public void blinkinRed() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }

    public void blinkinGreen() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
    }

    public void blinkinBlue() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }

    public void blinkinOrange() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
    }

    public void blinkinBlack() {
        leftLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        rightLights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }

}
