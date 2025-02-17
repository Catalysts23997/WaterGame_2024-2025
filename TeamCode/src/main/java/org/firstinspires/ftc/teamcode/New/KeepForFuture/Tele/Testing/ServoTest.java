package org.firstinspires.ftc.teamcode.New.KeepForFuture.Tele.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;


@Config
@TeleOp(name = "ClawTest", group = "Linear OpMode")
public class ServoTest extends LinearOpMode {

    public static double servoPose=0.0;
    public static int index=0;

    @Override
    public void runOpMode() {

        List<Servo> servoList = Arrays.asList(hardwareMap.get(Servo.class, "port0"), hardwareMap.get(Servo.class, "port1"), hardwareMap.get(Servo.class, "port2"), hardwareMap.get(Servo.class, "port3"), hardwareMap.get(Servo.class, "port4"));

        waitForStart();
        while (opModeIsActive()){
            servoList.get(index).setPosition(servoPose);
        }
    }
}

