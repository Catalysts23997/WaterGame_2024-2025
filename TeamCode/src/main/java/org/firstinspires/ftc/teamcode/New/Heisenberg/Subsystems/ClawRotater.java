package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;


import static java.lang.Double.max;
import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Utilities.Angle;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange;


public class ClawRotater {
    public Servo clawRotater;

    public ClawRotater(HardwareMap hardwareMap) {
        clawRotater = hardwareMap.get(Servo.class, "port3");
    }
//todo 180 degrees
    ServoRange servoRange = new ServoRange(0.0, PI *2,1.0);
    ServoPoseCalculator calc = new ServoPoseCalculator(servoRange);

    public void update(double angle) {
        double angleTarget = Angle.INSTANCE.wrap(angle -(1-0.14)*Math.PI);
        double position = (angleTarget+Math.PI) / (2 * PI);

        clawRotater.setPosition(position);
    }
}
