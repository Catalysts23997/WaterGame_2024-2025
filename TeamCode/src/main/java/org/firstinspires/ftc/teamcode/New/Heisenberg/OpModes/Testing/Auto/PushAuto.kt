package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.ReleasingActions
import org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Auto.AutoNewWork.Companion.robot_targetPosition
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExactForever
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.T
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@Autonomous(name = "PushAuto", group = "Auto")
class PushAuto : LinearOpMode() {

    companion object{
        var rT = Poses(19.0,-66.0,0.0)
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry
        TiegerAuto.rT = Poses(19.0,-66.0,0.0)

        val localizer = Localizer(hardwareMap, TiegerAuto.rT)
        val drive = Drive(hardwareMap)
        val robot = ReleasingActions(hardwareMap)

        T.autoType = false
        localizer.update()
        robot.update()

        waitForStart()

        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    RunToExactForever(TiegerAuto.rT)
                    telemetry.addData("hello", TiegerAuto.rT)
                    telemetry.addData("df", Localizer.pose.heading)
                    telemetry.addData("x", Localizer.pose.x)
                    telemetry.addData("y", Localizer.pose.y)
                    telemetry.update()
                    robot.update()
                    true
                },
                SequentialAction(
                    robot.Release,
                    Positions.Pos1.runToExact,
                    Positions.GotoSample1.runToExact,
                    Positions.PushtoSample1.runToExact,
                    Positions.GotoSample1.runToExact,
                    Positions.GotoSample2.runToExact,
                    Positions.PushtoSample2.runToExact,
                    Positions.GotoSample2.runToExact,
                    Positions.GotoSample3.runToExact,
                    Positions.PushtoSample3.runToExact
                )
            )
        )
    }
}
