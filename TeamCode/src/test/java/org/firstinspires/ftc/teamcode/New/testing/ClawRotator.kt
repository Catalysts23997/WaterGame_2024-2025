package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.PI
import kotlin.random.Random

class ClawRotator {

    /**
     * Given angle and returns servo pos, should be in between 1 and 0
     */
    @Test
    fun servoPoseBasedOnAngle() {

        for (i in 0..100) {
            val randomAngle = Random.nextDouble() * 2 * PI
            val servoCalc = ServoPoseCalculator(
                ServoRange(
                    Random.nextDouble(),
                    Random.nextDouble()
                )
            )
            assert(servoCalc.findPose(randomAngle) in 0.0..1.0)
        }

    }


    /**
     * Given random angle, servo pose is checked to accurately provide the correct position
     **/
    @Test
    fun servoPoseAt90Degrees() {

        for (i in 0..100) {
            val random = Random.nextDouble(0.0, Double.MAX_VALUE)
            val angle = 2 * PI / random
            val servoRange = ServoRange(
                Random.nextDouble(),
                Random.nextDouble()
            )
            val servoPoseCalc = ServoPoseCalculator(servoRange)

            val servoPose = (servoPoseCalc.findPose(angle))
            assertEquals(
                (servoRange.halfRotation - servoRange.zeroDegrees) / random + servoRange.zeroDegrees,
                servoPose,
                0.0
            )
        }

    }

}