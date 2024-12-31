package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import android.util.Log
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Angle
import org.firstinspires.ftc.teamcode.New.Controller
import org.firstinspires.ftc.teamcode.New.FindNearestPoint
import org.firstinspires.ftc.teamcode.New.PIDParams
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.SmoothInput
import org.firstinspires.ftc.teamcode.New.SubSystems
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin



//todo Make Java version
class Drive(hwMap: HardwareMap) : SubSystems {
    enum class States {
        Manual, Auto
    }

    val Xpid = Controller(PIDParams(0.2, 0.0001, 0.018, 0.0))
    val Ypid = Controller(PIDParams(0.2, 0.0001, 0.02, 0.0))
    val Rpid = Controller(PIDParams(1.4, 0.0001, 0.08, 0.0))

    override var state = States.Manual

    //todo note it will differ on new dt - (use customTest)
    val leftBack: DcMotor = hwMap.get(DcMotor::class.java, "rightBack") //good
    val leftFront: DcMotor = hwMap.get(DcMotor::class.java, "leftFront") // good
    val rightFront: DcMotor = hwMap.get(DcMotor::class.java, "leftBack") // good
    val rightBack: DcMotor = hwMap.get(DcMotor::class.java, "rightFront")

    override fun update(gamepadInput: ArrayList<Float>) {

        when (state) {
            States.Auto -> {
                //leave empty
            }

            States.Manual -> {
                driveManual(gamepadInput)
            }
        }
    }
//    fun setPID(p: DoubleArray, i: DoubleArray, d: DoubleArray) {
//        val controllers = listOf(Xpid, Ypid, Rpid)
//        controllers.forEachIndexed { index, controller ->
//            controller.params = PIDParams(p[index], i[index], d[index], 0.0)
//        }
//    }

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

    fun WheelDebugger(x: Int) {
        val y = arrayListOf(leftBack, leftFront, rightBack, rightFront)
        y[x].power = .5
    }

    fun StopRobot(){
        leftFront.power = 0.0
        leftBack.power = 0.0
        rightFront.power = 0.0
        rightBack.power = 0.0
    }

    private fun driveManual(gamepadInput: ArrayList<Float>) {
        val input = gamepadInput.map { SmoothInput.gamepadStick(it.toDouble()) }
        Log.d("f", input.toString())
        val (axial, lateral, turn) = input

        val h = -Localizer.pose.heading
        val rotX = axial * cos(h) - lateral * sin(h)
        val rotY = axial * sin(h) + lateral * cos(h)

        //todo add rotational pid


        leftFront.power = (rotY + rotX + turn)
        leftBack.power = (rotY + rotX - turn)
        rightFront.power = (rotY - rotX + turn)
        rightBack.power = (rotY - rotX - turn)
    }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: Drive
    }
}
