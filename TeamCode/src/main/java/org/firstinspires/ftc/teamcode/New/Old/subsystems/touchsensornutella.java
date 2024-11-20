package org.firstinspires.ftc.teamcode.New.Old.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.New.JavaSubsystems;

public class touchsensornutella implements JavaSubsystems {
    HardwareMap hardwareMap;
    TouchSensor righty;
    TouchSensor lefty;

    enum States {
        Both,
        Left,
        Right,
        None

    }

    States states = States.None;

    public touchsensornutella(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        righty = hardwareMap.get(TouchSensor.class, "righty");
        lefty = hardwareMap.get(TouchSensor.class, "lefty");
    }


    @Override
    public void update() {

        boolean rightP = righty.isPressed();
        boolean leftP = lefty.isPressed();

        if (rightP && leftP) {
            states = States.Both;
        }
        if (leftP) {
            states = States.Left;
        }
        if (rightP) {
            states = States.Right;
        }
        if (!(rightP && leftP)) {
            states = States.None;
        }

    }
}
