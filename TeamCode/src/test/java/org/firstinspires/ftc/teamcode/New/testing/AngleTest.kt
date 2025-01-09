package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.teamcode.New.Utilities.Angle
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random
import kotlin.math.PI

class AngleTest {

    @Test
    //Tests the wrap() function when the angle to wrap is within
    //the range -PI to PI.
    //Expected Outcome: Wrap should NOT occur.
    fun wrap_inside_range() {
        val angle = Angle
        val incr = 0.01
        val epsilon = 0.0

        //Test values that SHOULD NOT wrap

        //Test a bunch of values from -PI to PI.
        var expected = -Math.PI
        while (expected <= Math.PI) {
            val actual = angle.wrap(expected)

            assertEquals(expected, actual, epsilon)

            expected += incr
        }
    }

    @Test
    //Tests the wrap() function when the angle to wrap is outside
    //the range -PI to PI.
    //Expected Outcome: Wrap should occur.
    fun wrap_outside_range() {
        val angle = Angle
        val epsilon = 0.0

        val rng = Random()

        //Test that values > PI wrap
        for (i in 1..100) {
            val testValue = Math.PI + rng.nextDouble()
            val expected = testValue - Math.PI * 2
            val actual = angle.wrap(testValue)
            assertEquals(expected, actual, epsilon)
        }

        //Test that values < -PI wrap
        for (i in 1..100) {
            val testValue = -Math.PI - rng.nextDouble()
            val expected = testValue + Math.PI * 2
            val actual = angle.wrap(testValue)
            assertEquals(expected, actual, epsilon)
        }
    }

    @Test
    //Tests the wrapToPositive function with specific inputs in the 0-PI range
    fun wrapPositiveToPositiveAngles (){
        val random = kotlin.random.Random
        //Test that values > 2PI wrap
        for (i in 1..100) {
            val r = random.nextDouble(0.0, PI)
            assertEquals(r, Angle.wrapToPositive(r), 0.0)
        }
    }

    @Test
    //Tests the wrapToPositive function with specific inputs in the -PI to 0 range
    fun wrapNegativeToPositiveAngles (){
        val random = kotlin.random.Random
        //Test that values > 2PI wrap
        for (i in 1..100) {
            val r = random.nextDouble(-PI, 0.0)
            assertEquals(r + PI, Angle.wrapToPositive(r), 0.0)
        }
    }
}