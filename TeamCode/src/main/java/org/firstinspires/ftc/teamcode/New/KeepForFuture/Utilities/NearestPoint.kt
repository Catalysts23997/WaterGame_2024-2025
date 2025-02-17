package org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities

import com.acmerobotics.roadrunner.Vector2d
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

//length from center of robot to front in inches
private const val OFFSET = 7.0

/**
 * @param targetPos Target
 * @param currentPos Localizer Posewa
 */
fun findNearestPoint(targetPos: Vector2d, currentPos: Poses): Poses {

    //Does not work if target x is the same as currentx, will create a divide by 0 error.
    require(targetPos.x != currentPos.x) { "Target X is the same as Current X" }

    //triangle with legs A and C, hypotenuse B
    val A = targetPos.x - currentPos.x
    val C = targetPos.y - currentPos.y
    val B = sqrt(C.pow(2) + A.pow(2))

    //find destination's offset from currentPose
    val xOffsetFromTarget = OFFSET * (A.pow(2) + B.pow(2) - C.pow(2)) / (2 * A * B)
    val yOffsetTarget = sqrt(OFFSET.pow(2) - xOffsetFromTarget.pow(2))

    //Destinations Position
    val x = A - xOffsetFromTarget + currentPos.x
    val y = C - yOffsetTarget + currentPos.y
    val angle = acos((A.pow(2) + B.pow(2) - C.pow(2)) / (2 * A * B))

    return Poses(x, y, angle)
}