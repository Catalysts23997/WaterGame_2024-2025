package org.firstinspires.ftc.teamcode.New.Competition.subsystems;


import static java.lang.Math.toDegrees;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Angle;
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;

public class ClawRotater {

    public Servo clawRotater;

    double position = 0;

    public ClawRotater(HardwareMap hardwareMap){
        clawRotater = hardwareMap.get(Servo.class,"clawRotater");
    }

    double angleDegrees;
    public State state;

    public void update(Double angle) {
        switch (state){
            case ZERO: angleDegrees = 0; break;
            case INPUT: angleDegrees = angle; break;
            case ADJUSTING: angleDegrees = 90 - toDegrees(Localizer.pose.heading.toDouble()); break;
        }

        if(angleDegrees>=180){
            angleDegrees -= 180;
        }
        if(angleDegrees<0){
            angleDegrees += 180;
        }
        position = angleDegrees/180;
        clawRotater.setPosition(position);
    }

    double angle = 0;

    public void updateTele(Gamepad gamepad2){
        angle -= gamepad2.left_stick_y;
        if(angle>=180){
            angle -= 180;
        }
        if(angle<0){
            angle += 180;
        }

        //todo is math above just angle wrap? Why angle <0 PLEASE USE RADIANS
        //angle = Math.toDegrees(Angle.INSTANCE.wrap(Math.toRadians(angle)));
        //if(angle<0) angle += 180;

        position = angle/180;
        clawRotater.setPosition(position);
    }

    public enum State {
        ZERO,
        INPUT,
        ADJUSTING
    }

}
