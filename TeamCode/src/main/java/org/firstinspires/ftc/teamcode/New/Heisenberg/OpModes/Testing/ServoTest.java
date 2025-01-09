package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@TeleOp(name = "TestingServos", group = "Linear OpMode")
public class ServoTest extends LinearOpMode {
    List<Servo> servoList;

    @Override
    public void runOpMode() throws InterruptedException {


        servoList = Arrays.asList(hardwareMap.get(Servo.class, "port2"), hardwareMap.get(Servo.class, "port3"), hardwareMap.get(Servo.class, "port4"), hardwareMap.get(Servo.class, "port5"));

        // port 2:
        // port 3:


        waitForStart();


        while (opModeIsActive()) {
            if (gamepad1.a) {
                moveServo(0);
            }
            if (gamepad1.b) {
                moveServo(1);
            }
            if (gamepad1.x) {
                moveServo(2);
            }
            if (gamepad1.y) {
                moveServo(3);
            }
            if (gamepad1.right_bumper) {
                moveServo(4);
            }


        }
    }

    public void moveServo(int servoIndex) {
        Servo x = servoList.get(servoIndex);
        x.setPosition(0);
        sleep(2000);
        x.setPosition(1);
    }


}

