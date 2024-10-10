package org.firstinspires.ftc.teamcode.Kotlin_Bromine_Arya.Auto.PID_Components

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Rotation2d
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.Angle
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


class RunTo(private val targetVector: Vector2d, private val rotation: Rotation2d): Action {
    override fun run(p: TelemetryPacket): Boolean {
        val vector = Localizer.pose.position
        val heading = -1* Localizer.pose.heading.toDouble()
        val motors = PIDdrive.instance

//        val axial = Y.calculate(target.pose.y - vector.y)
//        val lateral = X.calculate(target.pose.x - vector.x)
//        val turn = R.calculate(target.heading.toDouble() +heading))
//        //note its + because heading is multiplied by negative 1
//
//        val rotX = lateral * cos(heading) - axial * sin(heading)
//        val rotY = lateral * sin(heading) + axial * cos(heading)

//        motors.leftFront.power = (rotY + rotX - turn)
//        motors.leftBack.power = (rotY - rotX - turn)
//        motors.rightFront.power = (rotY - rotX + turn)
//        motors.rightBack.power = (rotY + rotX + turn)

        return abs(vector.x - targetVector.x) <= 2.0 &&
                abs(vector.y - targetVector.y) <= 2.0 &&
                abs(Angle.wrap(rotation.toDouble() +heading)) <= Math.toRadians(8.0)
    }

}