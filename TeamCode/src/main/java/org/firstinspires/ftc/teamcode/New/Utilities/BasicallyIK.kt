package org.firstinspires.ftc.teamcode.New.Utilities

import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.sin

class BasicallyIK(private val wristLength: Double, private val armLength: Double) {
    fun findWristAngle(targetAngle: Double, armAngle: Double): Double {
        val angleA = armAngle - targetAngle
        val sideA = wristLength
        val sideB = armLength
        return 2 * PI + angleA + asin((sideB * sin(angleA)) / sideA)
    }
}
