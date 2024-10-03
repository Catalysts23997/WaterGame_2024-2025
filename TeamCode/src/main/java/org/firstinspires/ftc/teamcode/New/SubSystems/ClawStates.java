package org.firstinspires.ftc.teamcode.New.SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawStates implements JavaSubsystems {

    public RightClawjava rightClawsJava;
    public LeftClawjava leftClawsJava;
    public ClawStates(HardwareMap hardwareMap) {
        leftClawsJava = new LeftClawsJava(hardwareMap);
        rightClawsJava = new RightClawsJava(hardwareMap);
    }

    @Override
    public void update() {
        rightClawsJava.update();
        leftClawsJava.update();
    }

    public class RightClawjava {

        public RightClawjava(HardwareMap hardwareMap) {
            servo = hardwareMap.get(Servo.class, "rightclaw");

            Servo servo;

            @Override
            public void update () {
                switch (state) {

                    case Closed:
                        servo.setPosition(States.Closed.pose);
                        break;
                    case Open:
                        servo.setPosition(States.Open.pose);
                        break;

                }

            }
        }
        States state = States.Closed;
        enum States {
            Closed(.4), Open(.9);


            States(double input) {
                this.pose = input;
            }
            final double pose;
        }

    }


    public static class LeftClawsJava implements JavaSubsystems {
        public LeftClawsJava(HardwareMap hardwareMap) {
            servo = hardwareMap.get(Servo.class, "leftClaw");
        }


        Servo servo;


        @Override
        public void update() {


            switch (state){
                case Closed :
                    servo.setPosition(States.Closed.pose);
                    break;
                case Open :
                    servo.setPosition(States.Open.pose);
                    break;
            }


        }


        States state = States.Closed;
        enum States {
            Closed(.5), Open(1.0);


            States(double input) {
                this.pose = input;
            }
            final double pose;
        }

    }

}


