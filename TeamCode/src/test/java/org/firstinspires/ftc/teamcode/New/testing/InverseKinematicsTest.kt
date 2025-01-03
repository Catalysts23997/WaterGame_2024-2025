//package org.firstinspires.ftc.teamcode.New.testing
//
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import kotlin.math.sqrt
//
//class InverseKinematicsTest {
//
//
//
//    @Test
//    fun testFlat(){
//
//        val wristAngle: Double = findInverseKinematicsStuff(20.0,0.0,0.0).wristAngle
//        val slideAngle: Double = findInverseKinematicsStuff(20.0,0.0,0.0).slideAngle
//
//        assertEquals(wristAngle, Math.PI, 0.0)
//        assertEquals(slideAngle, 0.0, 0.0)
//
//    }
//
//    @Test
//    fun test306090(){
//
//        val wristAngle: Double = findInverseKinematicsStuff(10 * sqrt(3.0), 10.0,Math.PI/6).wristAngle
//        val slideAngle: Double = FindInverseKinematicsStuff.findInverseKinematicsStuff(10 * sqrt(3.0), 10.0,Math.PI/6).slideAngle
//
//        assertEquals(Math.PI, wristAngle, 0.1)
//        assertEquals(Math.PI/6, slideAngle, 0.1)
//
//    }
//}