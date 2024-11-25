package org.firstinspires.ftc.teamcode.New.testing

import com.acmerobotics.roadrunner.Vector2d
import org.firstinspires.ftc.teamcode.New.FindNearestPoint.findNearestPoint
import org.junit.Assert.assertEquals
import org.junit.Test

class OffsetPointTest {
    @Test
    fun linearTest() {
        var dist = findNearestPoint(Vector2d(10.0, 2.0), Vector2d(0.0, 2.0))


        assertEquals(5,dist.x.toInt())
        assertEquals(2,dist.y.toInt())

    }
}