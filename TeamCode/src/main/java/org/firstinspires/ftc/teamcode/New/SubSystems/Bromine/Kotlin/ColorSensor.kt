package org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Kotlin

import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.NormalizedColorSensor
import org.firstinspires.ftc.robotcore.external.JavaUtil
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

class ColorSensor(hwMap: HardwareMap) {

    enum class Recognition {
        IN,
        NOT
    }

    private val colorSensor: ColorSensor =
        hwMap.get(ColorSensor::class.java, "colorSensor")

    fun checkForRecognition(): Recognition {
        val colorSensorRecognition: Recognition

        //right sensor
        (colorSensor as NormalizedColorSensor).gain = 2f
        val normalizedColors = (colorSensor as NormalizedColorSensor).normalizedColors
        val color = normalizedColors.toColor()
        val value = JavaUtil.colorToValue(color)

        val inside =
            ((colorSensor as DistanceSensor).getDistance(DistanceUnit.CM) < 5) && value >= .06


        colorSensorRecognition =
            if (inside) Recognition.IN else Recognition.NOT

        return colorSensorRecognition
    }
}