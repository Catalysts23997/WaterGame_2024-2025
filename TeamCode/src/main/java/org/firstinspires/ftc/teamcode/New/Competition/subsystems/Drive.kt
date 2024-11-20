package org.firstinspires.ftc.teamcode.New.Competition.subsystems

import android.util.Log
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Angle
import org.firstinspires.ftc.teamcode.New.Controller
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

    val Xpid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))
    val Ypid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))
    val Rpid = Controller(PIDParams(0.0, 0.0, 0.0, 0.0))

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
    fun setPID(p: DoubleArray, i: DoubleArray, d: DoubleArray) {
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
        leftBack.direction = DcMotorSimple.Direction.REVERSE
        leftFront.direction = DcMotorSimple.Direction.REVERSE
        rightFront.direction = DcMotorSimple.Direction.FORWARD
        rightBack.direction = DcMotorSimple.Direction.FORWARD

    }

    fun WheelDebugger(x: Int) {
        val y = arrayListOf(leftBack, leftFront, rightBack, rightFront)
        y[x].power = .5
    }

    private fun driveManual(gamepadInput: ArrayList<Float>) {
        val input = gamepadInput.map { SmoothInput.gamepadStick(it.toDouble()) }
        Log.d("f", input.toString())
        val (axial, lateral, turn) = input

        val h = -Localizer.pose.heading
        val rotX = axial * cos(h) - lateral * sin(h)
        val rotY = axial * sin(h) + lateral * cos(h)

        leftFront.power = (rotY + rotX + turn)
        leftBack.power = (rotY - rotX + turn)
        rightFront.power = (rotY - rotX - turn)
        rightBack.power = (rotY + rotX - turn)
    }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: Drive
    }
}

class RunToExact(private val targetVector: Vector2d, private val rotation: Double) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        val current = Localizer.pose
        val drive = Drive.instance

        val lateral = drive.Ypid.calculate(targetVector.y - current.y)
        val axial = drive.Xpid.calculate(targetVector.x - current.x)
        val turn = drive.Rpid.calculate(Angle.wrap(rotation + current.heading))

//        Log.d("Y", doubleArrayOf(axial,lateral,turn, targetVector.y, current.y).contentToString())

        val h = -Localizer.pose.heading
        val rotX = axial * cos(h) - lateral * sin(h)
        val rotY = axial * sin(h) + lateral * cos(h)

        drive.leftFront.power = (rotY + rotX + turn)
        drive.leftBack.power = (rotY - rotX + turn)
        drive.rightFront.power = (rotY - rotX - turn)
        drive.rightBack.power = (rotY + rotX - turn)

        return !(abs(current.x - targetVector.x) <= 5.0 &&
                abs(current.y - targetVector.y) <= 5.0 &&
                abs(Angle.wrap(rotation + current.heading)) <= Math.toRadians(10.0))
    }
}
