package org.firstinspires.ftc.teamcode.New.testing

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sqrt
import org.firstinspires.ftc.teamcode.New.findNearestPoint
import org.firstinspires.ftc.teamcode.New.moveArmToPoint

class InverseKinematicsTest {



    @Test
    fun testFlat(){

        val wristAngle: Double = moveArmToPoint(20.0,0.0,0.0,10.0).armAngle
        val slideAngle: Double = moveArmToPoint(20.0,0.0,0.0,10.0).linkageDegree

        assertEquals(wristAngle, Math.PI, 0.0)
        assertEquals(slideAngle, 0.0, 0.0)

    }

    @Test
    fun test306090(){

        val wristAngle: Double = moveArmToPoint(10 * sqrt(3.0), 10.0,Math.PI/6,10.0).armAngle
        val slideAngle: Double = moveArmToPoint(10 * sqrt(3.0), 10.0,Math.PI/6,10.0).linkageDegree

        assertEquals(Math.PI, wristAngle, 0.1)
        assertEquals(Math.PI/6, slideAngle, 0.1)

    }
}