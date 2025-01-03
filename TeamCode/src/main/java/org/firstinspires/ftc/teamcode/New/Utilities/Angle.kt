package org.firstinspires.ftc.teamcode.New.Utilities

import kotlin.math.PI

object Angle {
    fun wrap(theta: Double): Double {
        var angle = theta
        while (angle > PI) angle -= PI * 2
        while (angle < -PI) angle += PI * 2
        return angle
    }

    fun wrapToPositive(theta: Double): Double {
        require(theta in -2 * PI..2 * PI)
        var angle = theta
        angle = wrap(angle)
        while (angle > PI) angle -= PI
        while (angle < 0) angle += PI
        return angle
    }
}