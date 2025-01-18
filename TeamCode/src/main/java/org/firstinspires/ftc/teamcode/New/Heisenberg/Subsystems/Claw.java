package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo claw;

    public ClawState clawState = ClawState.CLOSED;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "port5");
    }

    public void update(){
        claw.setPosition(clawState.servoPos);
    }

    public enum ClawState {
        CLOSED(.97),
        OPEN(.85);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }
}
