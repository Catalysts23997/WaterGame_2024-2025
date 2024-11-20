package org.firstinspires.ftc.teamcode.New.Old.subsystems;

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
        Wrist.setDirection(Servo.Direction.REVERSE);
    }

    //todo completely change the way wrist code is done - needs to be based off of arm angle
    //0 degree - 0.0 - vertical
    //180 degree - .62
    public enum State {
        Upwards(0.0),
        HpDrop(.7),
        Basket(.34),
        SamplePrep(0.6),
        WallIntake(0.65),
        Submersible(0.0); //not sure if we want to use arm angle or specified pose no matter angle
        public final double servoPos;

        State(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public void update() {
        double targetAngle = ShoudlerJohn.angle;

        if (state == State.Submersible) {
            targetAngle += Math.PI / 2 +.1;
        } else if(state == State.WallIntake){
            targetAngle -= Math.PI;
        }

        if (state == State.Submersible || state == State.WallIntake || state == State.Upwards && targetAngle >0 ) {
            Wrist.setPosition(ServoPoses.INSTANCE.findServoPosBasedOnAngle(
                    targetAngle, new ServoRange(0.94, 0.34)));
        }
        else Wrist.setPosition(state.servoPos);
        Log.d("YOO", String.valueOf(ShoudlerJohn.angle));
    }

}
