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

    public State state = State.ZERO;
    public static double angle = 0.0;

    ServoRange servoRange = new ServoRange(.34,1.0);
    ServoPoseCalculator calc = new ServoPoseCalculator(servoRange);

    public void update() {
        switch (state) {
            case ZERO: angle = 0;
                break;
            case INPUT: break;
            case ADJUSTING: angle = Angle.INSTANCE.wrapToPositive(Localizer.pose.getHeading());
                break;
            case HalfWay: angle = PI/2;
                break;
        }
        clawRotater.setPosition(calc.findPose(angle));
    }

    /**
     * Tele Update
     * @param left_stick_y Gamepad input (inverted)
     */
    public void update(double left_stick_y) {
        switch (state) {
            case ZERO: angle = 0;
                break;
            case INPUT: angle = PI/2 * (1+left_stick_y);
                break;
            case ADJUSTING: angle = Angle.INSTANCE.wrapToPositive(Localizer.pose.getHeading());
                break;
            case HalfWay: angle = PI/2;
                break;
        }
        clawRotater.setPosition(calc.findPose(angle));
    }

    public enum State {
        ZERO,
        INPUT,
        ADJUSTING,
        HalfWay
    }

}
