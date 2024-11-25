package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo claw;

    public ClawState clawState = ClawState.CLOSED;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void update(){
        claw.setPosition(clawState.servoPos);
    }

    public enum ClawState {
        CLOSED(0.0),
        OPEN(.14);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }
}
