package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WristJohn {
    public Servo Wrist;
    public State state = State.STATIONARY;

    public WristJohn(HardwareMap hardwareMap){
        Wrist = hardwareMap.get(Servo.class, "wrist");
    }

    //todo completely change the way wrist code is done - needs to be based off of arm angle
    //vertical pose =
    //full extended pose =
    public enum State{
        BASKET(0),
        CLIP(0),
        SUBMERSIBLE(0),
        GROUND(0),
        STATIONARY(0);

        public final double servoPos;
        State(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public double currentWrist;

    public void update(){
        Wrist.setPosition(state.servoPos);
        currentWrist = Wrist.getPosition();
    }


}
