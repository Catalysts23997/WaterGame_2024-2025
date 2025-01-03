package org.firstinspires.ftc.teamcode.New

import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


object Angle {
    fun wrap(theta: Double): Double {
        var angle = theta
        while (angle > PI) angle -= PI * 2
        while (angle < -PI) angle += PI * 2
        return angle
    }

    fun wrapToPositive(theta: Double): Double {
        require(theta in -2 * PI..2 * PI)
        var angle = theta
        angle = wrap(angle)
        while (angle > PI) angle -= PI
        while (angle < 0) angle += PI
        return angle
    }
}

data class Poses(val x: Double, val y: Double, val heading: Double)

data class PIDParams(val kp: Double, val ki: Double, val kd: Double, val kf: Double = 0.0)


object Wait {
    val wait: ElapsedTime = ElapsedTime()
    private var runOnce = false

//    fun waitFor(timeInMs: Int) {
//        if (timeStamp(timeInMs)) {
//            Sequencer.MAJORCOMMAND++
//        }
//    }

    fun <Parameter> runAsynchActionAfter(
        timeInMs: Int,
        action: (Parameter) -> Unit,
        parameter: Parameter
    ) {
        if (timeStamp(timeInMs)) action(parameter)
    }

    fun runAsynchActionAfter(timeInMs: Int, action: () -> Unit) {
        if (timeStamp(timeInMs)) action()
    }

    private fun timeStamp(timeInMs: Int): Boolean {
        var timeStamp = 0.0
        if (!runOnce) {
            timeStamp = wait.milliseconds()
        }
        runOnce = true

        return if (timeInMs <= wait.milliseconds() - timeStamp) {
            runOnce = false
            true
        } else false
    }

}

fun linearSlideExtension(gD: Double): AttachmentPositions {

    val clawLength = 6.5826772
    val armLength = 6.1338583
    val slidesFromGround = 2.976378
    val minLinkageDegree = 11.3809843


    val goalDistance = gD.coerceIn(12.0, 42.0)

    val hypotenuse =
        sqrt(goalDistance * goalDistance + (clawLength - slidesFromGround) * (slidesFromGround - clawLength))

    val currentLinkageDegree =
        if (minLinkageDegree < Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))) {
            10 + Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))
        } else {
            minLinkageDegree
        }

    val angleOppGround = Math.toDegrees(atan(goalDistance / (clawLength - slidesFromGround)))
    val angleOppArm: Double = currentLinkageDegree - (90 - angleOppGround)
    var insideArmAngle =
        Math.toDegrees(asin((hypotenuse * sin(Math.toRadians(angleOppArm))) / armLength))

    //if the arm length can possibly make two triangles because of ASS and if it is currently chosing the longer one, switch to the shortest triangle
    if ((armLength > hypotenuse * sin(Math.toRadians(angleOppArm))) && (armLength < hypotenuse) && (insideArmAngle < 90)) {
        insideArmAngle = 180 - insideArmAngle
    }
    val angleOppSlides = 180 - insideArmAngle - angleOppArm

    return AttachmentPositions(
        Math.toRadians(270 - angleOppGround - angleOppSlides),
        Math.toRadians(360 - insideArmAngle),
        (armLength * sin(Math.toRadians(angleOppSlides))) / sin(Math.toRadians(angleOppArm)),
        Math.toRadians(currentLinkageDegree)
    )
}


//todo make unit tests
//todo test full robot extension

//length from center of robot to front in inches
private const val OFFSET = 5.0

/**
 * @param targetPos Target
 * @param currentPos Localizer Posewa
 */
fun findNearestPoint(targetPos: Vector2d, currentPos: Poses): Poses {

    //Does not work if target x is the same as currentx, will create a divide by 0 error.
    require(targetPos.x != currentPos.x) { "Target X is the same as Current X" }

    //triangle with legs A and C, hypotenuse B
    val A = targetPos.x - currentPos.x
    val C = targetPos.y - currentPos.y
    val B = sqrt(C.pow(2) + A.pow(2))

    //find destination's offset from currentPose
    val xOffsetFromTarget = OFFSET * (A.pow(2) + B.pow(2) - C.pow(2)) / (2 * A * B)
    val yOffsetTarget = sqrt(OFFSET.pow(2) - xOffsetFromTarget.pow(2))

    //Destinations Position
    val x = A - xOffsetFromTarget + currentPos.x
    val y = C - yOffsetTarget + currentPos.y
    val angle = acos((A.pow(2) + B.pow(2) - C.pow(2)) / (2 * A * B))

    return Poses(x, y, angle)
}

data class ServoRange(val zeroDegrees: Double, val halfRotation: Double)
class ServoPoseCalculator(val servo: ServoRange) {
    fun findPose(
        angle: Double
    ): Double {
        return (((angle / (Math.PI)) * (servo.halfRotation - servo.zeroDegrees)) + servo.zeroDegrees).coerceIn(
            0.0,
            1.0
        )
    }
}

class BasicallyIK(private val wristLength: Double, private val armLength: Double) {
    fun findWristAngle(targetAngle: Double, armAngle: Double): Double {
        val angleA = armAngle - targetAngle
        val sideA = wristLength
        val sideB = armLength
        return 2 * PI + angleA + asin((sideB * sin(angleA)) / sideA)
    }
}

fun smoothGamepadInput(input: Double): Double {
    return if (input > 0) (input).pow(2.1) else -1 * abs(input).pow(2.1)
}


class Controller(var params: PIDParams) {
    private var prevError = 0.0
    private var integral = 0.0
    val timer = ElapsedTime()
    var pastTime = 0.0
    fun calculate(
        target: Double,
        armAngle: Double = 0.0
    ): Double {
        val dt = timer.seconds() - pastTime
        val error = target
        integral += (error * dt)

        val derivative = (error - prevError) / dt
        prevError = error

        val ff =
            if (params.kf != 0.0) if (armAngle < PI) max(0.0, sin(armAngle)) * params.kf else min(
                0.0,
                -sin(PI - (armAngle - PI))
            ) * params.kf else 0.0


        val controlEffort =
            ((derivative * params.kd + integral * params.ki + error * params.kp) + ff).coerceIn(
                -1.0,
                1.0
            )
        pastTime = timer.seconds()

//        Log.d("errorsss", ff.toString())
//        Log.d("errorsss", Math.toDegrees(armAngle).toString())
        return controlEffort
    }
}

val armLength = 6.1338583
val wristLength = 6.5826772


fun moveArmToPoint(
    targetX: Double,
    targetY: Double,
    wristAngle2: Double,
    slideLength: Double
): AttachmentPositions {

    val changeInX = wristLength * cos(wristAngle2)
    val changeInY = wristLength * sin(wristAngle2)

    val endEffectorX = targetX - changeInX
    val endEffectorY = targetY - changeInY

    val wristAngle1 =
        acos((armLength.pow(2) + slideLength.pow(2) - endEffectorX.pow(2) - endEffectorY.pow(2)) / (2 * slideLength * armLength))

    val slideAngle = atan(endEffectorY / endEffectorX) + acos(
        (slideLength.pow(2) + endEffectorY.pow(2) + endEffectorX.pow(2) - armLength.pow(2)) / (2 * slideLength * sqrt(
            endEffectorX.pow(2) + endEffectorY.pow(2)
        ))
    )


    return AttachmentPositions(
        wristAngle2,
        wristAngle1,
        slideLength,
        slideAngle
    )
}


//note circumference is inputted as mm
class SlidesEncoderConv(var circumference: Double) {

    val ticksPerRev: Double = 28.0
    val inPerMm: Double = 0.0393701

    fun toIn(ticks: Double): Double {
        return (ticks / ticksPerRev) * circumference * inPerMm
    }

}

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

