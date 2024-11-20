package org.firstinspires.ftc.teamcode.New.Old.OpModes.Testing.Opmodes.ColorSensor;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Old.subsystems.ColorSensorDrake;

@Disabled
@TeleOp(name="Color Sensor OpMode", group="Sensor")
public class ColorSensorOpMode extends LinearOpMode {
    ColorSensorDrake colorSensorSubsystem;


    @Override
    public void runOpMode() {
        colorSensorSubsystem = new ColorSensorDrake();
        colorSensorSubsystem.initColorSensor(hardwareMap);


        waitForStart();


        while (opModeIsActive()) {
            colorSensorSubsystem.update();


            switch (colorSensorSubsystem.states) {
                case Detected:
                    telemetry.addData("Color Detected", "Red");
                    break;
                case Left:
                    telemetry.addData("Color Detected", "Green or Blue on Left Sensor");
                    break;
                case Right:
                    telemetry.addData("Color Detected", "Green or Blue on Right Sensor");
                    break;
                default:
                    telemetry.addData("Color Detected", "None");
                    break;
            }


            telemetry.update();
        }
    }
}
