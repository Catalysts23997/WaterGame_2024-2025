package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.New.SubSystems.JavaSubsystems;


// TODO Review

public class ColorSensor implements JavaSubsystems {


    HardwareMap hardwareMap;
    com.qualcomm.robotcore.hardware.ColorSensor LeftS;
    com.qualcomm.robotcore.hardware.ColorSensor RightS;


    enum States {
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


    ColorSensor.States states = ColorSensor.States.Off;


    // This is how we detect colors based on RGB values
    public boolean isRed(com.qualcomm.robotcore.hardware.ColorSensor sensor) {
        return sensor.red() > sensor.green() && sensor.red() > sensor.blue();
    }


    public boolean isGreen(com.qualcomm.robotcore.hardware.ColorSensor sensor) {
        return sensor.green() > sensor.red() && sensor.green() > sensor.blue();
    }


    public boolean isBlue(com.qualcomm.robotcore.hardware.ColorSensor sensor) {
        return sensor.blue() > sensor.red() && sensor.blue() > sensor.green();
    }


    @Override
    public void update() {
        states = ColorSensor.States.Running;


        // Left Sensor Detection System
        if (isRed(LeftS)) {
            System.out.println("Left Sensor Detected Red");
            states = ColorSensor.States.Detected;
        } else if (isGreen(LeftS)) {
            System.out.println("Left Sensor Detected Green");
            states = ColorSensor.States.Left;
        } else if (isBlue(LeftS)) {
            System.out.println("Left Sensor Detected Blue");
            states = ColorSensor.States.Left;
        }


        // Right Sensor Detection System
        if (isRed(RightS)) {
            System.out.println("Right Sensor Detected Red");
            states = ColorSensor.States.Detected;
        } else if (isGreen(RightS)) {
            System.out.println("Right Sensor Detected Green");
            states = ColorSensor.States.Right;
        } else if (isBlue(RightS)) {
            System.out.println("Right Sensor Detected Blue");
            states = ColorSensor.States.Right;
        }
    }
}

