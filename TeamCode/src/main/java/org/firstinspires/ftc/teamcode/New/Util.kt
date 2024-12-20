package org.firstinspires.ftc.teamcode.New

import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositons
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.CollectionOfAttachments
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.SystemAngles
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
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
        while (angle>PI) angle -= PI
        while (angle<0) angle += PI
        return angle
    }
}

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

object Slides{
    fun linearSlideExtension(gD: Double): AttachmentPositons {

        val clawLength = 6.5826772
        val armLength = 6.1338583
        val slidesFromGround = 2.976378
        val maxLinkageDegree = 11.3809843


        val goalDistance = gD.coerceIn(12.0,42.0)

        val hypotenuse =
            sqrt(goalDistance * goalDistance + (clawLength - slidesFromGround) * (slidesFromGround - clawLength))

        CollectionOfAttachments.slideDegree = if (CollectionOfAttachments.slideDegree < Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))) {
            10 + Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))
        } else {
            maxLinkageDegree
        }

        val angleOppGround = Math.toDegrees(atan(goalDistance / (clawLength - slidesFromGround)))
        val angleOppArm: Double = CollectionOfAttachments.slideDegree - (90 - angleOppGround)
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
     * @param currentPos Localizer Posewa
     */
    fun findNearestPoint(targetPos: Vector2d, currentPos: Localizer.Poses): Localizer.Poses {

        //Does not work if target x is the same as currentx, will create a divide by 0 error.
        require(targetPos.x != currentPos.x) {"Target X is the same as Current X"}

        //triangle with legs A and C, hypotenuse B
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

        return Localizer.Poses(x, y, angle)
    }

}
data class ServoRange(val zeroDegrees: Double, val halfRotation:Double)
class ServoPoseCalculator(val servo: ServoRange){
    fun findPose(
        angle: Double
    ): Double {
        return (((angle / (Math.PI)) * (servo.halfRotation - servo.zeroDegrees)) + servo.zeroDegrees).coerceIn(0.0,1.0)
    }
}

class BasicallyIK(private val wristLength: Double, private val armLength: Double) {
    fun findWristAngle(targetAngle :Double, armAngle: Double): Double{
        val angleA = armAngle - targetAngle
        val sideA = wristLength
        val sideB = armLength
        return 2* PI + angleA + asin((sideB*sin(angleA))/sideA)
    }
}
object SmoothInput{
    fun gamepadStick(input: Double): Double{
        return if(input>0)(input).pow(2.1)  else -1 * abs(input).pow(2.1)
    }
}

class Controller(var params: PIDParams) {
    private var prevError = 0.0
    private var integral = 0.0
    val timer = ElapsedTime()
    var pastTime=0.0
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
            if(params.kf !=0.0) if(armAngle< PI ) max(0.0, sin(armAngle)) * params.kf else min(0.0, -sin(PI-(armAngle - PI))) * params.kf else 0.0


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

object FindInverseKinematicsStuff{

    //todo replace these with actual values

    val wristLength1 = 5.0
    val wristLength2 = 5.0

    val slideLength = 10.0

    fun findInverseKinematicsStuff(targetX: Double, targetY: Double, wristAngle2: Double):SystemAngles {
        val changeInX = wristLength2 * cos(wristAngle2)
        val changeInY = wristLength2 * sin(wristAngle2)

        val endEffectorX = targetX - changeInX
        val endEffectorY = targetY - changeInY

        val wristAngle1 = acos((wristLength1.pow(2) + slideLength.pow(2) - endEffectorX.pow(2) - endEffectorY.pow(2))/(2 * slideLength * wristLength1))

        val slideAngle = atan(endEffectorY/endEffectorX) + acos((slideLength.pow(2) + endEffectorY.pow(2) + endEffectorX.pow(2) - wristLength1.pow(2))/(2 * slideLength * sqrt(endEffectorX.pow(2) + endEffectorY.pow(2))))


        return SystemAngles(wristAngle1, slideAngle)
    }
}

