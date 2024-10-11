package com.example.simulation

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import kotlin.math.acos
import kotlin.math.pow
import kotlin.math.sqrt

object FindNearestPoint {
    //todo make unit tests

    //length from center of robot to front in inches
    private const val OFFSET = 5.0

    fun noObstacles(targetPos: Vector2d, currentPos: Vector2d): Pose2d{

        //triangle with legs A and A, hypotenuse B
        val A = targetPos.x - currentPos.x
        val C = targetPos.y - currentPos.y
        val B = sqrt(C.pow(2) + A.pow(2))

        //find destination's offset from currentPose
        val xOffsetFromTarget = OFFSET*(A.pow(2)+B.pow(2)-C.pow(2))/(2*A*B)
        val yOffsetTarget = sqrt(OFFSET.pow(2) - xOffsetFromTarget.pow(2))

        //Destinations Position
        val x = A - xOffsetFromTarget + currentPos.x
        val y = C - yOffsetTarget + currentPos.y
        val angle = acos((A.pow(2)+B.pow(2)-C.pow(2)) /(2*A*B))

        return Pose2d(x,y,angle)
    }

}