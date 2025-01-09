package org.firstinspires.ftc.teamcode.New.Utilities

import android.opengl.Matrix
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions
import kotlin.math.cos
import kotlin.math.sin


data class Point2D(val x: Double, val y: Double)
data class Point3D(
    val x: Double, val y: Double, val z: Double
)

data class CameraParams(
    val fx: Double,
    val fy: Double,
    val cx: Double,
    val cy: Double
)

fun pixelToCameraCoords(pixel: Point2D, cameraParams: CameraParams): Point3D {
    // Convert pixel to normalized camera coordinates
    val x = (pixel.x - cameraParams.cx) / cameraParams.fx
    val y = (pixel.y - cameraParams.cy) / cameraParams.fy
    return Point3D(x, y, 1.0) // Assume z = 1 for normalized coordinates
}

fun projectToGround(cameraCoords: Point3D, cameraPosition: Point3D): Point2D {
    // Check for potential division by zero
    if (cameraPosition.z == cameraCoords.z) {
        throw ArithmeticException("Camera is at the same height as the ground.")
    }

    // Calculate the scaling factor to project onto the ground
    val scale = cameraPosition.z / (cameraPosition.z - cameraCoords.z)
    val xGround = cameraCoords.x * scale + cameraPosition.x
    val yGround = cameraCoords.y * scale + cameraPosition.y
    return Point2D(xGround, yGround)
}


fun findPositionOfSample(cameraPosition: Point3D, pixelCoordinates: Point2D): Point2D {

    val cameraParams = CameraParams(
        fx = 800.0,  // Focal length in x
        fy = 800.0,  // Focal length in y
        cx = 320.0,  // Principal point x (image center)
        cy = 240.0   // Principal point y (image center)
    )

    // Step 1: Convert 2D pixel coordinates to camera coordinates (normalized)
    val cameraCoords = pixelToCameraCoords(pixelCoordinates, cameraParams)

    // Step 2: Project the camera coordinates onto the ground plane
    return projectToGround(cameraCoords, cameraPosition)
}

fun findCameraPosition(positions: AttachmentPositions): Point3D {
    val lengths = doubleArrayOf(positions.slideLength, 6.1338583, 6.5826772)
    val ratios = arrayOf(
        doubleArrayOf(cos(positions.linkageAngle), sin(positions.linkageAngle)),
        doubleArrayOf(cos(positions.linkageAngle - Math.PI + positions.armAngle), sin(positions.linkageAngle - Math.PI + positions.armAngle)),
        doubleArrayOf(cos(positions.clawAngle), sin(positions.clawAngle))
    )
    val products = multiply1DMatrixBy2D(lengths, ratios)

    val x = products[0]
    val y = 0.0
    val z = products[1]

    return Point3D(x,y,z)
}


