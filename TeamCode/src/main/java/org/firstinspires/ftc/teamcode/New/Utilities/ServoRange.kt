package org.firstinspires.ftc.teamcode.New.Utilities
data class ServoRange(val zeroDegrees: Double, val halfRotation: Double)
class ServoPoseCalculator(val servo: ServoRange) {
    fun findPose(
        angle: Double
    ): Double {
        return (((angle / (Math.PI)) * (servo.halfRotation - servo.zeroDegrees)) + servo.zeroDegrees).coerceIn(
            0.0,
            1.0
        )
    }
}