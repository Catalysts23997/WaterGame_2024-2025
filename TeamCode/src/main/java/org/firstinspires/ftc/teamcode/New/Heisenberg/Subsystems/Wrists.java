package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrists {
    public Servo Elbow;
    public Servo Wrist;
    public State state = State.DropSample;

    public Wrists(HardwareMap hardwareMap) {
        Elbow = hardwareMap.get(Servo.class, "port0");
        Elbow.setDirection(Servo.Direction.FORWARD);
        Wrist = hardwareMap.get(Servo.class, "port2");
        Wrist.setDirection(Servo.Direction.FORWARD);
    }

    //1.0 intake back

    public enum State {
        Basket(0.5,.5),
        IntakeFront(0.35,0.0),
        IntakeBack(0.97,0.9),
        IntakeWall(0.35,0.4),
        SpecimenHang(0.35,0.0),
        DropSample(0.35,0.4);
        public final double servoPos;
        public final double nextPose;

        State(double servoPos, double nextPose) {
            this.servoPos = servoPos;
            this.nextPose= nextPose;
        }
    }

    public void update() {
        Elbow.setPosition(state.servoPos);
        Wrist.setPosition(state.nextPose);
    }

}
