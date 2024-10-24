package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.SubSystems.JavaSubsystems;

public class VerticalWrist implements JavaSubsystems {
    HardwareMap hardwareMap;
    Servo wristvert;

    public enum Position{
        Top(1),
        Middle(0),
        Bottom(-1),
        ;

        Position(int i) {
            this.servoPos = i;
        }
        public final int servoPos;
    }
    public VerticalWrist.Position position = Position.Middle;

    public  VerticalWrist(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
        hardwareMap.get(Servo.class,"wristvert");


    }

    @Override
    public void update() {
        switch (position){
            case Top: wristvert.setPosition(Position.Top.servoPos);
            break;
            case Bottom: wristvert.setPosition(Position.Bottom.servoPos);
            break;
            case Middle: wristvert.setPosition(Position.Middle.servoPos);
            break;
        }

    }

//    public void update (Gamepad object) {
//        max= 1;
//        min= -1;
//        gamepad = object;
//        if(gamepad.y){
//            oldposition = wristvert.getPosition();
//            wristvert.setPosition(oldposition + .25);
//            if(oldposition >= 1){
//                wristvert.setPosition(max);
//            }
//            update();
//        }
//        if(gamepad.a){
//            oldposition = wristvert.getPosition();
//            wristvert.setPosition(oldposition-.25);
//            update();
//            if (oldposition <= -1){
//                wristvert.setPosition(min);
//            }
//
//        }
//
//        wristvert.setPosition(0);
//
//  not necessary for code to run.
//
//
//    }
}


