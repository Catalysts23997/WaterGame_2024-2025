package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions

import android.util.Log
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Angle
import org.firstinspires.ftc.teamcode.New.Controller
import org.firstinspires.ftc.teamcode.New.FindNearestPoint.findNearestPoint
import org.firstinspires.ftc.teamcode.New.PIDParams
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

//todo bring in PIDF class

class PIDdrive(hwMap: HardwareMap) {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: PIDdrive
    }

    //constants
    val Xpid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))
    val Ypid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))
    val Rpid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))

    val rightBack: DcMotor = hwMap.get(DcMotorEx::class.java, "rightBack")
    val leftFront: DcMotor = hwMap.get(DcMotorEx::class.java, "leftFront")
    val rightFront: DcMotor = hwMap.get(DcMotorEx::class.java, "rightFront")
    val leftBack: DcMotor = hwMap.get(DcMotorEx::class.java, "leftBack")

    fun setPID(p: DoubleArray, i: DoubleArray, d: DoubleArray) {
        Log.d("PID", "setting PID")
        val controllers = listOf(Xpid, Ypid, Rpid)
        controllers.forEachIndexed { index, controller ->
            controller.params = PIDParams(p[index], i[index], d[index], 0.0)
        }
    }

    init {
        leftBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftBack.direction = DcMotorSimple.Direction.FORWARD
        leftFront.direction = DcMotorSimple.Direction.REVERSE
        rightFront.direction = DcMotorSimple.Direction.REVERSE
        rightBack.direction = DcMotorSimple.Direction.FORWARD
    }


}

/**
 * Runs to the target provided, based on the robots extension
 */