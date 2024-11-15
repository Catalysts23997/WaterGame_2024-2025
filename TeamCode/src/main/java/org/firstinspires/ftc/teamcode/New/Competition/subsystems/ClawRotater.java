package org.firstinspires.ftc.teamcode.New.Competition.subsystems;


import static java.lang.Math.PI;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Angle;
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;
import org.firstinspires.ftc.teamcode.New.ServoPoses;
import org.firstinspires.ftc.teamcode.New.ServoRange;

public class ClawRotater {
    public Servo clawRotater;

    public ClawRotater(HardwareMap hardwareMap) {
        clawRotater = hardwareMap.get(Servo.class, "clawRotator");
    }

    public State state = State.ZERO;
    public static double angle = 0.0;

    public void update() {
        switch (state) {
            case ZERO:
                angle = 0;
                break;
            //todo setup/change
            case INPUT:
                break;
            case ADJUSTING:
                angle = Angle.INSTANCE.wrap(PI - Localizer.pose.heading.toDouble());
                break;
        }
        angle = Angle.INSTANCE.wrapToPositive(angle);
        clawRotater.setPosition(ServoPoses.INSTANCE.findServoPosBasedOnAngle(angle, new ServoRange(start, end)));
    }

    double start = .3176;
    double end = 1.0;

    public void updateTele(double left_stick_y) {
        angle -= left_stick_y * .02;
        angle = Angle.INSTANCE.wrapToPositive(angle);
        clawRotater.setPosition(ServoPoses.INSTANCE.findServoPosBasedOnAngle(angle, new ServoRange(start, end)));
    }

    public enum State {
        ZERO,
        INPUT,
        ADJUSTING
    }

}
