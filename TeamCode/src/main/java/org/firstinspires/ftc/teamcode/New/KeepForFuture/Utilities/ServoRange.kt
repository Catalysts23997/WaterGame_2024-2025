package org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities
data class ServoRange(val zeroDegrees: Double, val rotationAngle: Double, val rotationPosition: Double)
class ServoPoseCalculator(private val servo: ServoRange) {
    fun findPose(
        angle: Double
    ): Double {
        return (((angle / (servo.rotationAngle)) * (servo.rotationPosition - servo.zeroDegrees)) + servo.zeroDegrees).coerceIn(
            0.0,
            1.0
        )
    }
}