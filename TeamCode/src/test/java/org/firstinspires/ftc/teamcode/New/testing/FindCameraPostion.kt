package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.teamcode.New.Joint
import org.firstinspires.ftc.teamcode.New.Point3D
import org.firstinspires.ftc.teamcode.New.computeCameraPosition
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.PI

class FindCameraPositionTest {

    // Test 1: Test with a simple setup
    @Test
    fun testComputeCameraPositionSimple() {
        val theta1 = PI / 4  // 45 degrees
        val theta2 = PI / 3  // 60 degrees
        val theta3 = PI / 6  // 30 degrees
        val T1 = 5.0  // Length of first link
        val T2 = 4.0  // Length of second link
        val T3 = 3.0  // Length of third link
        val cameraOffset = Point3D(0.5, 0.0, 0.2)  // Camera offset

        val joint1 = Joint(theta1, T1)
        val joint2 = Joint(theta2, T2)
        val joint3 = Joint(theta3, T3)

        val cameraPosition = computeCameraPosition(
            cameraOffset,
            joint1,
            joint2,
            joint3
        )

        // You can adjust these expected values as per your calculation
        val expectedPosition = Point3D(8.2321, 1.0607, 0.6) // Adjust based on your own transformations
        assertEquals(expectedPosition.x, cameraPosition.x, 0.0001)
        assertEquals(expectedPosition.y, cameraPosition.y, 0.0001)
        assertEquals(expectedPosition.z, cameraPosition.z, 0.0001)
    }

    // Test 2: Test with zero offset
    @Test
    fun testComputeCameraPositionZeroOffset() {
        val theta1 = PI / 4
        val theta2 = PI / 3
        val theta3 = PI / 6
        val T1 = 5.0
        val T2 = 4.0
        val T3 = 3.0
        val cameraOffset = Point3D(0.0, 0.0, 0.0)  // Zero offset

        val joint1 = Joint(theta1, T1)
        val joint2 = Joint(theta2, T2)
        val joint3 = Joint(theta3, T3)

        val cameraPosition = computeCameraPosition(
            cameraOffset,
            joint1,
            joint2,
            joint3
        )

        // Expected position without offset (this value should be computed based on your system)
        val expectedPosition = Point3D(8.2321, 1.0607, 0.0)
        assertEquals(expectedPosition.x, cameraPosition.x, 0.0001)
        assertEquals(expectedPosition.y, cameraPosition.y, 0.0001)
        assertEquals(expectedPosition.z, cameraPosition.z, 0.0001)
    }

    // Test 3: Test with a negative offset
    @Test
    fun testComputeCameraPositionNegativeOffset() {
        val theta1 = PI / 4
        val theta2 = PI / 3
        val theta3 = PI / 6
        val T1 = 5.0
        val T2 = 4.0
        val T3 = 3.0
        val cameraOffset = Point3D(-0.5, -0.5, -0.2)  // Negative offset

        val joint1 = Joint(theta1, T1)
        val joint2 = Joint(theta2, T2)
        val joint3 = Joint(theta3, T3)

        val cameraPosition = computeCameraPosition(
            cameraOffset,
            joint1,
            joint2,
            joint3
        )

        // You would adjust this expected value based on your own transformation and offset
        val expectedPosition = Point3D(7.7321, 0.5607, -0.2) // Example values
        assertEquals(expectedPosition.x, cameraPosition.x, 0.0001)
        assertEquals(expectedPosition.y, cameraPosition.y, 0.0001)
        assertEquals(expectedPosition.z, cameraPosition.z, 0.0001)
    }

    // Test 4: Test with a single joint
    @Test
    fun testComputeCameraPositionSingleJoint() {
        val theta1 = PI / 4
        val T1 = 5.0
        val cameraOffset = Point3D(1.0, 0.0, 0.0)  // Offset of 1 unit on the X-axis

        val joint1 = Joint(theta1, T1)

        val cameraPosition = computeCameraPosition(cameraOffset, joint1)

        // Expected position based on this single joint transformation
        val expectedPosition = Point3D(4.7071, 0.0, 0.0)
        assertEquals(expectedPosition.x, cameraPosition.x, 0.0001)
        assertEquals(expectedPosition.y, cameraPosition.y, 0.0001)
        assertEquals(expectedPosition.z, cameraPosition.z, 0.0001)
    }

    // Test 5: Test with multiple joints and a camera offset in the z-axis
    @Test
    fun testComputeCameraPositionMultipleJoints() {
        val theta1 = PI / 4
        val theta2 = PI / 3
        val theta3 = PI / 6
        val T1 = 5.0
        val T2 = 4.0
        val T3 = 3.0
        val cameraOffset = Point3D(0.0, 0.0, 0.5)  // Offset of 0.5 along the z-axis

        val joint1 = Joint(theta1, T1)
        val joint2 = Joint(theta2, T2)
        val joint3 = Joint(theta3, T3)

        val cameraPosition = computeCameraPosition(
            cameraOffset,
            joint1,
            joint2,
            joint3
        )

        // Example expected position based on the transformation with a z-axis offset
        val expectedPosition = Point3D(8.2321, 1.0607, 0.5) // Adjust values based on your calculations
        assertEquals(expectedPosition.x, cameraPosition.x, 0.0001)
        assertEquals(expectedPosition.y, cameraPosition.y, 0.0001)
        assertEquals(expectedPosition.z, cameraPosition.z, 0.0001)
    }
}
