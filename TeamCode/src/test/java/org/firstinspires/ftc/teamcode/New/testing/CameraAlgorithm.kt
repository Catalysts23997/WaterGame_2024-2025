package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.teamcode.New.Utilities.CameraParams
import org.firstinspires.ftc.teamcode.New.Utilities.Point2D
import org.firstinspires.ftc.teamcode.New.Utilities.Point3D
import org.firstinspires.ftc.teamcode.New.Utilities.pixelToCameraCoords
import org.firstinspires.ftc.teamcode.New.Utilities.projectToGround
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException

class CameraAlgorithmTest {

    @Rule
    @JvmField
    val expectedException = ExpectedException.none()

    private val epsilon = 1e-5 // Tolerance for floating point comparisons

    @Test
    fun testPixelToCameraCoords() {
        val cameraParams = CameraParams(fx = 800.0, fy = 800.0, cx = 320.0, cy = 240.0)
        val pixel = Point2D(x = 400.0, y = 300.0)

        val expected = Point3D(x = 0.1, y = 0.075, z = 1.0) // Normalize coordinates assuming z = 1
        val result = pixelToCameraCoords(pixel, cameraParams)

        // Compare with epsilon for floating point precision
        assertEquals(expected.x, result.x, epsilon)
        assertEquals(expected.y, result.y, epsilon)
        assertEquals(expected.z, result.z, epsilon)
    }

    @Test
    fun testProjectToGround() {
        val cameraCoords =
            Point3D(x = 0.1, y = 0.075, z = 1.0) // Camera coordinates in normalized space
        val cameraPosition =
            Point3D(x = 5.0, y = 5.0, z = 10.0) // Camera positioned above the ground

        // Correct expected result after projection, with small epsilon for comparison
        val expected = Point2D(x = 5.11111, y = 5.08333)
        val result = projectToGround(cameraCoords, cameraPosition)

        // Compare with epsilon for floating point precision
        assertEquals(expected.x, result.x, epsilon)
        assertEquals(expected.y, result.y, epsilon)
    }

    @Test
    fun testProjectToGroundWhenCameraIsAtSameHeight() {
        val cameraCoords = Point3D(x = 0.1, y = 0.075, z = 1.0)
        val cameraPosition =
            Point3D(x = 5.0, y = 5.0, z = 1.0) // Same height as the camera coordinate

        // Set up the expected exception using the ExpectedException rule
        expectedException.expect(ArithmeticException::class.java)
        expectedException.expectMessage("Camera is at the same height as the ground.")

        // This should throw the exception
        projectToGround(cameraCoords, cameraPosition)
    }
}
