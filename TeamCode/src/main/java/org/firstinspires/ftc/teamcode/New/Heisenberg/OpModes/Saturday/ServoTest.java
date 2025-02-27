package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@TeleOp(name = "ServoTest", group = "Linear OpMode")
public class ServoTest extends LinearOpMode {
    List<Servo> servoList;

    @Override
    public void runOpMode() throws InterruptedException {


        servoList = Arrays.asList(hardwareMap.get(Servo.class, "port2"), hardwareMap.get(Servo.class, "port3"), hardwareMap.get(Servo.class, "port4"), hardwareMap.get(Servo.class, "port5"));

        // port 2: wrist: 1 is 25 degrees below parallel with arm, 0 is 115 from parallel
        // port 3: rotates claw, doesn't work
        // port 4: armservo1 1 is 10 degrees left of positive y axis, 0 is 45 degrees left of negative y axis
        // port 5: armservo2 0 is straight up, 1 is 45 left negative y axis degrees

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
        }
    }

    public void moveServo(int servoIndex) {
        Servo x = servoList.get(servoIndex);
        x.setPosition(0);
        sleep(2000);
        x.setPosition(1);
    }


}

