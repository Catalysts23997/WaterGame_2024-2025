package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    Servo claw;

    public ClawState clawState;

    public Claw(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "Claw");
    }

    public double clawEncoder;

    public void update(){
        claw.setPosition(clawState.servoPos);
        clawEncoder = claw.getPosition();
    }

    public enum ClawState {
        CLOSED(0.5),
        OPEN(0);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }
}
