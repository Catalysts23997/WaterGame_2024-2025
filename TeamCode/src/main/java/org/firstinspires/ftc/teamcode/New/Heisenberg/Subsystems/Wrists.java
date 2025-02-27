package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrists {
    public Servo Elbow;
    public Servo Wrist;
    public State state = State.IntakeFront;

    public Wrists(HardwareMap hardwareMap) {
        Elbow = hardwareMap.get(Servo.class, "port0");
        Elbow.setDirection(Servo.Direction.FORWARD);
        Wrist = hardwareMap.get(Servo.class, "port2");
        Wrist.setDirection(Servo.Direction.FORWARD);
    }

    //1.0 intake back

    public enum State {
        Basket(0.5,1.0),
        IntakeFront(0.6,0.45),
        IntakeWall(0.0,1.0),
        In(0.6,1.0),
        SpecimenHang(0.6,1.0),
        DropSample(0.6,.8);
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
