package org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Vector2d
import org.firstinspires.ftc.teamcode.New.Angle
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.findNearestPoint
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class RunToExact(private val targetVector: Vector2d, private val rotation: Double) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        val current = Localizer.pose
        val drive = Drive.instance

        val latError = targetVector.y - current.y
        val axialError = targetVector.x - current.x
        val headingError = Angle.wrap(rotation + current.heading)

        val lateral = drive.Ypid.calculate(latError)
        val axial = drive.Xpid.calculate(axialError)
        val turn = drive.Rpid.calculate(headingError)

//        Log.d("Y", doubleArrayOf(axial,lateral,turn, targetVector.y, current.y).contentToString())

        val h = -Localizer.pose.heading
        val rotX = axial * cos(h) - lateral * sin(h)
        val rotY = axial * sin(h) + lateral * cos(h)

        drive.leftFront.power = (rotY + rotX + turn)
        drive.leftBack.power = (rotY - rotX + turn)
        drive.rightFront.power = (rotY - rotX - turn)
        drive.rightBack.power = (rotY + rotX - turn)

        return !(arrayListOf(axialError, latError).all { abs(it) <= 3.0 } &&
                abs(headingError) <= Math.toRadians(5.0))
    }
}

class RunToNearest(private val targetVector: Vector2d) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        val current = Localizer.pose
        val drive = Drive.instance

        val newTarget = findNearestPoint(targetVector, current)

        val latError = newTarget.y - current.y
        val axialError = newTarget.x - current.x
        val headingError = Angle.wrap(newTarget.heading + current.heading)

        val lateral = drive.Ypid.calculate(latError)
        val axial = drive.Xpid.calculate(axialError)
        val turn = drive.Rpid.calculate(headingError)

        val h = -Localizer.pose.heading
        val rotX = axial * cos(h) - lateral * sin(h)
        val rotY = axial * sin(h) + lateral * cos(h)

        drive.leftFront.power = (rotY + rotX + turn)
        drive.leftBack.power = (rotY - rotX + turn)
        drive.rightFront.power = (rotY - rotX - turn)
        drive.rightBack.power = (rotY + rotX - turn)

        return !(abs(latError) <= 5.0 &&
                abs(axialError) <= 5.0 &&
                abs(Angle.wrap(headingError)) <= Math.toRadians(5.0))
    }
}