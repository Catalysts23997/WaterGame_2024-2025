package org.firstinspires.ftc.teamcode.New.Competition.Actions

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Rotation2d
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Angle
import org.firstinspires.ftc.teamcode.New.FindNearestPoint.findNearestPoint
import kotlin.math.abs

//todo bring in PIDF class

class PIDdrive(hwMap: HardwareMap){

    init {
        instance = this
    }
    companion object{
        lateinit var instance: PIDdrive
    }

    //constants
//   private val X: PIDController = PIDController()
//    private val Y: PIDController = PIDController()
//    private val R: PIDController = PIDController()

    private val rightBack: DcMotor = hwMap.get(DcMotorEx::class.java, "rightBack")
    private val leftFront: DcMotor = hwMap.get(DcMotorEx::class.java, "leftFront")
    private val rightFront: DcMotor = hwMap.get(DcMotorEx::class.java, "rightFront")
    private val leftBack: DcMotor = hwMap.get(DcMotorEx::class.java, "leftBack")

    fun setPID(p: DoubleArray, i: DoubleArray, d:DoubleArray){
//        X.setPIDF(p[0], i[0], d[0], 0.0)
//        Y.setPIDF(p[1], i[1], d[0], 0.0)
//        R.setPIDF(p[2], i[2], d[0], 0.0)
    }

    init {
        leftBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftBack.direction = DcMotorSimple.Direction.REVERSE
        leftFront.direction = DcMotorSimple.Direction.REVERSE
    }



}

/**
 * Runs to the target provided, based on the robots extension
 */
class RunToNearest(private val targetVector: Vector2d, private val rotation: Rotation2d): Action {
    override fun run(p: TelemetryPacket): Boolean {
        val currentVector = Localizer.pose.position
        val heading = -1* Localizer.pose.heading.toDouble()
        val motors = PIDdrive.instance
        val target = findNearestPoint(targetVector, currentVector).position

//        val axial = Y.calculate(target.y - currentVector.y)
//        val lateral = X.calculate(target.x - currentVector.x)
//        val turn = R.calculate(rotation.toDouble() +heading)
//        //note its + because heading is multiplied by negative 1
//
//        val rotX = lateral * Math.cos(heading) - axial * Math.sin(heading)
//        val rotY = lateral * Math.sin(heading) + axial * Math.cos(heading)
//
//        motors.leftFront.power = (rotY + rotX - turn)
//        motors.leftBack.power = (rotY - rotX - turn)
//        motors.rightFront.power = (rotY - rotX + turn)
//        motors.rightBack.power = (rotY + rotX + turn)

        return abs(currentVector.x - targetVector.x) <= 2.0 &&
                abs(currentVector.y - targetVector.y) <= 2.0 &&
                abs(Angle.wrap(rotation.toDouble() +heading)) <= Math.toRadians(8.0)
    }

}

/**
 * Runs to exact point
 */
class RunToExact(private val targetVector: Vector2d, private val rotation: Rotation2d): Action {
    override fun run(p: TelemetryPacket): Boolean {
        val currentVector = Localizer.pose.position
        val heading = -1* Localizer.pose.heading.toDouble()
        val motors = PIDdrive.instance
//
//        val axial = Y.calculate(targetVector.y - currentVector.y)
//        val lateral = X.calculate(targetVector.x - currentVector.x)
//        val turn = R.calculate(rotation.heading.toDouble() +heading)
//        //note its + because heading is multiplied by negative 1
//
//        val rotX = lateral * Math.cos(heading) - axial * Math.sin(heading)
//        val rotY = lateral * Math.sin(heading) + axial * Math.cos(heading)
//
//        motors.leftFront.power = (rotY + rotX - turn)
//        motors.leftBack.power = (rotY - rotX - turn)
//        motors.rightFront.power = (rotY - rotX + turn)
//        motors.rightBack.power = (rotY + rotX + turn)

        return abs(currentVector.x - targetVector.x) <= 2.0 &&
                abs(currentVector.y - targetVector.y) <= 2.0 &&
                abs(Angle.wrap(rotation.toDouble() +heading)) <= Math.toRadians(8.0)
    }

}