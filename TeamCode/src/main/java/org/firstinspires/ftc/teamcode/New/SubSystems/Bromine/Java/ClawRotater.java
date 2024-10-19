package org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawRotater {

    Servo clawRotater;

    double position = 0;

    public ClawRotater(HardwareMap hardwareMap){
        clawRotater = hardwareMap.get(Servo.class,"clawRotater");
    }

    public void update(Double angleDegrees){
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
        angle += gamepad2.left_stick_y;
        if(angle>=180){
            angle -= 180;
        }
        if(angle<0){
            angle += 180;
        }
        position = angle/180;
        clawRotater.setPosition(position);
    }

}
