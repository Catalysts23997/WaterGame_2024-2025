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
        Basket(0.3,.3),
        IntakeGround(0.0,0.7),
        IntakeWall(0.0,0.7),
        DropSample(0.3,.7);
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
