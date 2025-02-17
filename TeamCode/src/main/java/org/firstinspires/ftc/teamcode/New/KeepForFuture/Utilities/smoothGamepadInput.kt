package org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities

import kotlin.math.abs
import kotlin.math.pow

fun smoothGamepadInput(input: Double): Double {
    return if (input > 0) (input).pow(2.1) else -1 * abs(input).pow(2.1)
}
