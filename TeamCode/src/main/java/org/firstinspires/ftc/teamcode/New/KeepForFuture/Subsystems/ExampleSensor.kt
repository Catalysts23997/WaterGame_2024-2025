package org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems

import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DistanceSensor

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit


class ExampleSensor(hwMap: HardwareMap) {


    //this creates the instance of the sensor that is taken from the robot
    private val sensor: DistanceSensor = hwMap.get(DistanceSensor::class.java, "Sensor")

    //create functions for all the main outputs of the sensor.
    fun IsWithinValue(states: States): Boolean {
        return sensor.getDistance(DistanceUnit.INCH) < states.value
    }

    //states with values stored
    enum class States(values: Double) {
        ONE(1.0),
        TWO(2.0),
        THREE(3.0);
        val value = values
    }


    //TODO:
    //for color sensors make multiple boolean that recognizes any requested color (use paramaters and enums)

    //For ultrasonic have first a boolean function of being within an inputted distance (use paramaters)
    //Second, have a double function outputting the exact distance

    //Have a simple boolean function (pressed or not) for the limit switch

}