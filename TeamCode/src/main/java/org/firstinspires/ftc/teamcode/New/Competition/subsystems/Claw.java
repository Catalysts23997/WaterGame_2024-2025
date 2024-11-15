package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo claw;

    public ClawState clawState = ClawState.OPEN;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void update(){
        claw.setPosition(clawState.servoPos);
    }

    public enum ClawState {
        CLOSED(0.83),
        OPEN(1.0);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }
}
