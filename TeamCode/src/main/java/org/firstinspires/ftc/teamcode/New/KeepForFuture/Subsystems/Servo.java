package org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Servo {
    public com.qualcomm.robotcore.hardware.Servo servo;
    public ClawState clawState = ClawState.CLOSED;

    public Servo(HardwareMap hardwareMap){
        servo = hardwareMap.get(com.qualcomm.robotcore.hardware.Servo.class, "port4");
    }

    public void update(){
        servo.setPosition(clawState.servoPos);
    }

    public enum ClawState {
        CLOSED(1.0),
        OPEN(.85);
        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }
}
