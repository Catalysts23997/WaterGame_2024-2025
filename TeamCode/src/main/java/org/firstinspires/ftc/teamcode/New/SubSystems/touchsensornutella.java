package org.firstinspires.ftc.teamcode.New.SubSystems.Java;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class touchsensornutella implements JavaSubsystems {
    HardwareMap hardwareMap;
    TouchSensor righty;
    TouchSensor lefty;
    boolean rightyispressed;
    boolean leftyispressed;

    enum States {
        Both,
        Left,
        Right,
        None

    }

    touchsensornutella.States states = States.None;

    public touchsensornutella(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        righty = hardwareMap.get(TouchSensor.class, "righty");
        lefty = hardwareMap.get(TouchSensor.class, "lefty");
//        righty = new TouchSensor(hardwareMap);
//        leftClawsJava = new(hardwareMap);
    }


    @Override
    public void update() {

        switch (states) {
            case None:
                update();
                break;
            case Right:
                update();
                break;

            case Both:
                update();
                break;
            case Left:
                update();
                break;
        }

        states = States.Both;
        if (righty.isPressed() && lefty.isPressed()) {
            states = States.Both;

        }
        states = States.Right;
        if (righty.isPressed()) {
            states = States.Right;

        }

        states = States.None;
        if (!rightyispressed && !leftyispressed) {
            states = States.None;
        }

        // sets up righty true false
        if (righty.isPressed()) {
            rightyispressed = true;

        } else {
            rightyispressed = false;
        }

        // sets up lefty true false
        if (lefty.isPressed()) {
            leftyispressed = true;

        } else {
            leftyispressed = false;
        }


    }
}
