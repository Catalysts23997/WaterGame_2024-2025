package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.robotcore.internal.system.Assert.assertFalse
import org.firstinspires.ftc.robotcore.internal.system.Assert.assertTrue
import org.firstinspires.ftc.teamcode.New.Utilities.Poses
import org.junit.Test
import kotlin.math.PI

class PosesTest {

    @Test
    fun `test equals with identical poses`() {
        val pose1 = Poses(1.0, 2.0, Math.toRadians(10.0))
        val pose2 = Poses(1.0, 2.0, Math.toRadians(10.0))

        // Since both poses are identical, the equals method should return true
        assertTrue(pose1 == pose2, "Poses should be equal")
    }

    @Test
    fun `test equals with different positions`() {
        val pose1 = Poses(1.0, 2.0, Math.toRadians(10.0))
        val pose2 = Poses(4.0, 5.0, Math.toRadians(10.0))

        // The positions differ, so the equals method should return false
        assertFalse(pose1 == pose2, "Poses should not be equal due to different positions")
    }

    @Test
    fun `test equals with different headings`() {
        val pose1 = Poses(1.0, 2.0, Math.toRadians(10.0))
        val pose2 = Poses(1.0, 2.0, Math.toRadians(16.0))

        // The headings differ beyond the tolerance of 5 degrees, so the equals method should return false
        assertFalse(pose1 == pose2, "Poses should not be equal due to different headings")
    }

    @Test
    fun `test equals with within tolerance positions and headings`() {
        val pose1 = Poses(1.0, 2.0, Math.toRadians(10.0))
        val pose2 = Poses(1.0+1.4, 2.0 + 2.9, Math.toRadians(14.0 ))

        // The positions and heading are within the tolerance, so the equals method should return true
        assertTrue(pose1 == pose2, "Poses should be equal within the tolerance")
    }
}
