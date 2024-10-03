package org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Java.JavaSubsystems;
import org.firstinspires.ftc.teamcode.New.SubSystems.JavaSubsystems;

public class TouchSensor implements JavaSubsystems {
    HardwareMap hardwaremap;
    com.qualcomm.robotcore.hardware.TouchSensor RightT;
    com.qualcomm.robotcore.hardware.TouchSensor LeftT;

    enum States {
        Both,
        Left,
        Right,
        None,

    }

    TouchSensor.States states = States.None;

    public TouchSensor(HardwareMap hardwareMap) {
        this.hardwaremap = hardwareMap;
        RightT = hardwareMap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, "RS");
        LeftT = hardwaremap.get(com.qualcomm.robotcore.hardware.TouchSensor.class, "LS");

    }


    @Override
    public void update() {
        states = States.Left;
        if (LeftT.isPressed() == false) {
            telemetry.addData("LS", "1");
        } else {
            telemetry.addData("LS", "0");
        }
        states = States.Right;
        if (RightT.isPressed() == false) {
            telemetry.addData("RS", "1");
        } else {
            telemetry.addData("RS", "0");
        }



    }


}
