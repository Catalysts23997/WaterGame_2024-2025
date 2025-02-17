package org.firstinspires.ftc.teamcode.New.KeepForFuture.Auto

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Vector2d
import org.firstinspires.ftc.teamcode.New.KeepForFuture.PinpointLocalizer.Localizer

import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.Opmodes.Auto.ExampleAuto
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Angles
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Poses
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.findNearestPoint
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class RunToExact(private val pose: Poses) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        val current = Localizer.pose
        val drive = Drivetrain.instance

        val latError = pose.y - current.y
        val axialError = pose.x - current.x
        val headingError = Angles.wrap(pose.heading + current.heading)

        val lateral = drive.Ypid.calculate(latError)
        val axial = drive.Xpid.calculate(axialError)
        val turn = drive.Rpid.calculate(headingError)

//        Log.d("Y", doubleArrayOf(axial,lateral,turn, targetVector.y, current.y).contentToString())

        val h = -Localizer.pose.heading
        val rotX = -axial * cos(h) - lateral * sin(h)
        val rotY = -axial * sin(h) + lateral * cos(h)

        //todo add rotational pid

        drive.leftFront.power = (rotY - rotX - turn)
        drive.leftBack.power = (rotY + rotX - turn)
        drive.rightFront.power = (rotY + rotX + turn)
        drive.rightBack.power = (rotY - rotX + turn)

        return !(arrayListOf(axialError, latError).all { abs(it) <= 1.0 } &&
                abs(headingError) <= Math.toRadians(5.0))
    }
}

class RunToNearest(private val targetVector: Vector2d) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        val current = Localizer.pose
        val drive = Drivetrain.instance

        val newTarget = findNearestPoint(targetVector, current)

        val latError = newTarget.y - current.y
        val axialError = newTarget.x - current.x
        val headingError = Angles.wrap(newTarget.heading + current.heading)

        val lateral = drive.Ypid.calculate(latError)
        val axial = drive.Xpid.calculate(axialError)
        val turn = drive.Rpid.calculate(headingError)

        val h = -Localizer.pose.heading
        val rotX = -axial * cos(h) - lateral * sin(h)
        val rotY = -axial * sin(h) + lateral * cos(h)

        //todo add rotational pid

        drive.leftFront.power = (rotY - rotX - turn)
        drive.leftBack.power = (rotY + rotX - turn)
        drive.rightFront.power = (rotY + rotX + turn)
        drive.rightBack.power = (rotY - rotX + turn)

        return !(abs(latError) <= 3.0 &&
                abs(axialError) <= 3.0 &&
                abs(Angles.wrap(headingError)) <= Math.toRadians(4.0))
    }
}

fun RunToExactForever(pose: Poses): Boolean {
        val current = Localizer.pose
        val drive = Drivetrain.instance

        val latError = pose.y - current.y
        val axialError = pose.x - current.x
        val headingError = Angles.wrap(pose.heading + current.heading)

        val lateral = drive.Ypid.calculate(latError)
        val axial = drive.Xpid.calculate(axialError)
        val turn = drive.Rpid.calculate(headingError)

//        Log.d("Y", doubleArrayOf(axial,lateral,turn, targetVector.y, current.y).contentToString())

        val h = -Localizer.pose.heading
        val rotX = -axial * cos(h) - lateral * sin(h)
        val rotY = -axial * sin(h) + lateral * cos(h)

        //todo add rotational pid

        drive.leftFront.power = (rotY - rotX - turn)
        drive.leftBack.power = (rotY + rotX - turn)
        drive.rightFront.power = (rotY + rotX + turn)
        drive.rightBack.power = (rotY - rotX + turn)

    return true
}

object T {
    var autoType = true
}

class SetDriveTarget(val pose: Poses):Action{
    override fun run(p: TelemetryPacket): Boolean {
        ExampleAuto.rT = pose

        return !(abs( ExampleAuto.rT.x -Localizer.pose.x) <= 3.0 &&
                abs( ExampleAuto.rT.y-Localizer.pose.y) <= 3.0 &&
                abs(Angles.wrap(ExampleAuto.rT.heading +Localizer.pose.heading)) <= Math.toRadians(5.0))
    }
}