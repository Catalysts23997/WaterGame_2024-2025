package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange;


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
