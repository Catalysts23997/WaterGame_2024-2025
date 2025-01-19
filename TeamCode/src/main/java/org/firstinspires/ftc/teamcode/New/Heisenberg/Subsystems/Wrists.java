package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import android.util.Log;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange;

public class Wrists {
    public Servo Wrist;
    public Servo Wrist2;
    public State state = State.DropSample;

    public Wrists(HardwareMap hardwareMap) {
        Wrist = hardwareMap.get(Servo.class, "port2");
        Wrist.setDirection(Servo.Direction.FORWARD);
        Wrist2 = hardwareMap.get(Servo.class, "port4");
        Wrist2.setDirection(Servo.Direction.FORWARD);
    }

    public enum State {
        Basket(1.0,.4),
        IntakeGround(.37,.53),
        IntakeWall(0.53,.53),
        DropSample(.8,.53);
        public final double servoPos;
        public final double nextPose;

        State(double servoPos, double nextPose) {
            this.servoPos = servoPos;
            this.nextPose= nextPose;
        }
    }

    public void update() {
        Wrist.setPosition(state.servoPos);
        Wrist2.setPosition(state.nextPose);
    }

}
