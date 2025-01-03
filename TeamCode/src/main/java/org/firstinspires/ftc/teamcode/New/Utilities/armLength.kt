package org.firstinspires.ftc.teamcode.New.Utilities

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions
import kotlin.math.acos
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

val armLength = 6.1338583
val wristLength = 6.5826772


fun moveArmToPoint(
    targetX: Double,
    targetY: Double,
    wristAngle2: Double,
    slideLength: Double
): AttachmentPositions {

    val changeInX = wristLength * cos(wristAngle2)
    val changeInY = wristLength * sin(wristAngle2)

    val endEffectorX = targetX - changeInX
    val endEffectorY = targetY - changeInY

    val wristAngle1 =
        acos((armLength.pow(2) + slideLength.pow(2) - endEffectorX.pow(2) - endEffectorY.pow(2)) / (2 * slideLength * armLength))

    val slideAngle = atan(endEffectorY / endEffectorX) + acos(
        (slideLength.pow(2) + endEffectorY.pow(2) + endEffectorX.pow(2) - armLength.pow(2)) / (2 * slideLength * sqrt(
            endEffectorX.pow(2) + endEffectorY.pow(2)
        ))
    )


    return AttachmentPositions(
        wristAngle2,
        wristAngle1,
        slideLength,
        slideAngle
    )
}
