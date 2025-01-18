package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import android.util.Log;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange;

public class Wrists {
    public Servo Wrist;
    public Servo Wrist2;
    public State state;

    public Wrists(HardwareMap hardwareMap) {
        Wrist = hardwareMap.get(Servo.class, "wrist");
        Wrist.setDirection(Servo.Direction.FORWARD);
        Wrist2 = hardwareMap.get(Servo.class, "wrist2");
        Wrist2.setDirection(Servo.Direction.FORWARD);
    }

    public enum State {
        WallGrab(0.0),
        Intake(.7),
        Deposit(.34),
        Stationary(0.6);
        public final double servoPos;

        State(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    ServoRange servoRange = new ServoRange(.94,Math.PI,.34);
    ServoPoseCalculator calc = new ServoPoseCalculator(servoRange);

    public void update() {
        Wrist.setPosition(state.servoPos);
        Wrist2.setPosition(0);
    }

}
