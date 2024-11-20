package org.firstinspires.ftc.teamcode.New.Old.subsystems;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.JavaSubsystems;


// TODO Review

public class ColorSensorDrake implements JavaSubsystems {

    ColorSensor LeftS;
    ColorSensor RightS;


    public enum ColorSensorStates {
        Running,
        Off,
        Detected,
        Left,
        Right
    }


    public void initColorSensor(HardwareMap hardwareMap) {
        RightS = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, "RC");
        LeftS = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class, "LC");
    }


    public ColorSensorStates states = ColorSensorStates.Off;


    // This is how we detect colors based on RGB values
    public boolean isRed(ColorSensor sensor) {
        return sensor.red() > sensor.green() && sensor.red() > sensor.blue();
    }


    public boolean isGreen(ColorSensor sensor) {
        return sensor.green() > sensor.red() && sensor.green() > sensor.blue();
    }


    public boolean isBlue(ColorSensor sensor) {
        return sensor.blue() > sensor.red() && sensor.blue() > sensor.green();
    }


    @Override
    public void update() {
        states = ColorSensorStates.Running;

        // Left Sensor Detection System
        if (isRed(LeftS)) {
            System.out.println("Left Sensor Detected Red");
            states = ColorSensorStates.Detected;
        } else if (isGreen(LeftS)) {
            System.out.println("Left Sensor Detected Green");
            states = ColorSensorStates.Left;
        } else if (isBlue(LeftS)) {
            System.out.println("Left Sensor Detected Blue");
            states = ColorSensorStates.Left;
        }


        // Right Sensor Detection System
        if (isRed(RightS)) {
            System.out.println("Right Sensor Detected Red");
            states = ColorSensorStates.Detected;
        } else if (isGreen(RightS)) {
            System.out.println("Right Sensor Detected Green");
            states = ColorSensorStates.Right;
        } else if (isBlue(RightS)) {
            System.out.println("Right Sensor Detected Blue");
            states = ColorSensorStates.Right;
        }
    }
}

