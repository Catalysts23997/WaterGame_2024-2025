package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;


import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Angle;
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;
import org.firstinspires.ftc.teamcode.New.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.ServoRange;

public class ClawRotater {
    public Servo clawRotater;

    public ClawRotater(HardwareMap hardwareMap) {
        clawRotater = hardwareMap.get(Servo.class, "clawRotator");
    }


    ServoRange servoRange = new ServoRange(.34, 1.0);
    ServoPoseCalculator calc = new ServoPoseCalculator(servoRange);

    public void update(double angle) {
        clawRotater.setPosition(calc.findPose(angle));
    }
}
