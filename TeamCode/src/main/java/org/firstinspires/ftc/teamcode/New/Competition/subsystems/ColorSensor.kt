package org.firstinspires.ftc.teamcode.New.Competition.subsystems

import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.NormalizedColorSensor
import org.firstinspires.ftc.robotcore.external.JavaUtil
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit

class ColorSensor(hwMap: HardwareMap) {

    private val colorSensor: ColorSensor =
        hwMap.get(ColorSensor::class.java, "colorSensor")

    fun checkForRecognition(): Boolean {

        //right sensor
        (colorSensor as NormalizedColorSensor).gain = 2f
        val normalizedColors = (colorSensor as NormalizedColorSensor).normalizedColors
        val color = normalizedColors.toColor()
        val value = JavaUtil.colorToValue(color)

        val inside =
            ((colorSensor as DistanceSensor).getDistance(DistanceUnit.CM) < 7) && value >= .04

        return inside
    }
}