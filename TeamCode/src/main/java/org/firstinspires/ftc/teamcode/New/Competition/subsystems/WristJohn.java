package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import android.util.Log;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.ServoPoses;
import org.firstinspires.ftc.teamcode.New.ServoRange;

public class WristJohn {
    public Servo Wrist;
    public State state = State.Upwards;

    public WristJohn(HardwareMap hardwareMap) {
        Wrist = hardwareMap.get(Servo.class, "wrist");
    }

    //todo completely change the way wrist code is done - needs to be based off of arm angle
    //0 degree - 0.0 - vertical
    //180 degree - .62
    public enum State {
        Upwards(0.0),
        HpDrop(.27),
        Basket(0.0),
        SamplePrep(0.2),
        WallIntake(0.0),
        Submersible(0.0); //not sure if we want to use arm angle or specified pose no matter angle
        public final double servoPos;

        State(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public void update() {
        double targetAngle = ShoudlerJohn.angle;

        if (state == State.Submersible || state == State.WallIntake) {
            targetAngle += Math.PI / 2;
        }

        if (state == State.Submersible || state == State.WallIntake || state == State.Upwards && targetAngle >0 ) {
            Wrist.setPosition(ServoPoses.INSTANCE.findServoPosBasedOnAngle(
                    targetAngle, new ServoRange(0.0, 0.62)));
        }
        else Wrist.setPosition(state.servoPos);
        Log.d("YOO", String.valueOf(ShoudlerJohn.angle));
    }

}
