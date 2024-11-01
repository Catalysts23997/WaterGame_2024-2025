package org.firstinspires.ftc.teamcode.New

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AttachmentPositons
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AttachmentsJohn
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
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
        while (angle>PI) angle -= PI
        while (angle<0) angle += PI
        return angle
    }
}

data class PIDParams(val kp: Double, val ki: Double, val kd: Double, val kf: Double = 0.0)

data class PIDFcontroller(var params: PIDParams) {

    private var integral = 0.0
    private val timer: ElapsedTime = ElapsedTime()
    private var previousError = 0.0
    fun calculate(error: Double): Double {

        integral += (error * timer.seconds())
        val derivative = (error - previousError) / timer.seconds()
        timer.reset()

        //add FF
        return (derivative * params.kd + integral * params.ki + error * params.kp).coerceIn(
            -1.0,
            1.0
        )
    }
}


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

object Slides{
    fun linearSlideExtension(gD: Double): AttachmentPositons {

        val clawLength = 6.5826772
        val armLength = 6.1338583
        val slidesFromGround = 2.976378
        val maxLinkageDegree = 11.3809843


        val goalDistance = gD.coerceIn(12.0,42.0)

        val hypotenuse =
            sqrt(goalDistance * goalDistance + (clawLength - slidesFromGround) * (slidesFromGround - clawLength))

        AttachmentsJohn.slideDegree = if (AttachmentsJohn.slideDegree < Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))) {
            10 + Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))
        } else {
            maxLinkageDegree
        }

        val angleOppGround = Math.toDegrees(atan(goalDistance / (clawLength - slidesFromGround)))
        val angleOppArm: Double = AttachmentsJohn.slideDegree - (90 - angleOppGround)
        var insideArmAngle =
            Math.toDegrees(asin((hypotenuse * sin(Math.toRadians(angleOppArm))) / armLength))

        //if the arm length can possibly make two triangles because of ASS and if it is currently chosing the longer one, switch to the shortest triangle
        if ((armLength > hypotenuse * sin(Math.toRadians(angleOppArm))) && (armLength < hypotenuse) && (insideArmAngle < 90)) {
            insideArmAngle = 180 - insideArmAngle
        }
        val angleOppSlides = 180 - insideArmAngle - angleOppArm

        return AttachmentPositons(
            (270 - angleOppGround - angleOppSlides),
            (360 - insideArmAngle),
            (armLength * sin(Math.toRadians(angleOppSlides))) / sin(Math.toRadians(angleOppArm))
        )

    }
}

object FindNearestPoint {
    //todo make unit tests
    //todo test full robot extension

    //length from center of robot to front in inches
    private const val OFFSET = 5.0

    /**
     * @param targetPos Target
     * @param currentPos Localizer Pose
     */
    fun findNearestPoint(targetPos: Vector2d, currentPos: Vector2d): Pose2d {

        //triangle with legs A and A, hypotenuse B
        val A = targetPos.x - currentPos.x
        val C = targetPos.y - currentPos.y
        val B = sqrt(C.pow(2) + A.pow(2))

        //find destination's offset from currentPose
        val xOffsetFromTarget = OFFSET*(A.pow(2)+B.pow(2)-C.pow(2))/(2*A*B)
        val yOffsetTarget = sqrt(OFFSET.pow(2) - xOffsetFromTarget.pow(2))

        //Destinations Position
        val x = A - xOffsetFromTarget + currentPos.x
        val y = C - yOffsetTarget + currentPos.y
        val angle = acos((A.pow(2) + B.pow(2) - C.pow(2)) / (2 * A * B))

        return Pose2d(x, y, angle)
    }

}
data class ServoRange(val zeroDegrees: Double, val halfRotation:Double)
object ServoPoses{
    fun findServoPosBasedOnAngle(
        angle: Double,
        servo: ServoRange
    ): Double {
        return ((angle / (2 * Math.PI)) * (servo.halfRotation - servo.zeroDegrees)) + servo.zeroDegrees
    }
}

class BasicallyIK(private val wristLength: Double, private val armLength: Double) {
    //todo make unit tests
    fun findWristAngle(targetAngle :Double, armAngle: Double): Double{
        val angleA = armAngle - targetAngle
        val sideA = wristLength
        val sideB = armLength
        return 2* PI + angleA + asin((sideB*sin(angleA))/sideA)
    }
}
object SmoothInput{
    fun gamepadStick(input: Double): Double{
        return if(input>0) input.pow(2.1) else -input.pow(2.1)
    }
}