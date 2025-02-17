package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.ReleasingActions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExactForever
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.T
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@Autonomous(name = "TiegerAuto", group = "Auto")
class TiegerAuto : LinearOpMode() {

    companion object{
        var rT = Poses(19.0,-66.0,0.0)
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry
        TiegerAuto.rT = Poses(19.0,-66.0,0.0)

        val robot = ReleasingActions(hardwareMap)
        val localizer = Localizer(hardwareMap, TiegerAuto.rT)
        val drive = Drivetrain(hardwareMap)

        T.autoType = false

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
                    Positions.Specimin1.runToExact,
                    SleepAction(1.0),
                    Positions.Sample1.runToExact,
                    robot.ForwardIntake,
                    SleepAction(1.0),
                    SequentialAction(
                        robot.GrabPre, SleepAction(1.3), robot.Grab
                    ),
                    SleepAction(.5),
                    Positions.Player1.runToExact,
                    robot.Release,
                    Positions.Sample2.runToExact,
                    SleepAction(.5),
                    SequentialAction(
                        robot.GrabPre, SleepAction(1.3), robot.Grab
                    ),
                    SleepAction(.5),
                    Positions.Player2.runToExact,
                    robot.Release,
                    Positions.Sample3.runToExact,
                    SleepAction(.5),
                    SequentialAction(
                        robot.GrabPre, SleepAction(1.3), robot.Grab
                    ),
                    SleepAction(.5),
                    Positions.Player3.runToExact,
                    Positions.WallIntake.runToExact,
                    SleepAction(1.0),
                    Positions.Specimin2.runToExact,
                    SleepAction(1.0),
                    Positions.WallIntake.runToExact,
                    SleepAction(1.0),
                    Positions.Specimin3.runToExact,
                    SleepAction(1.0),
                    Positions.WallIntake.runToExact,
                    SleepAction(1.0),
                    Positions.Specimin4.runToExact,
                    SleepAction(1.0),
                    Positions.WallIntake.runToExact,
                    SleepAction(1.0),
                    Positions.Specimin5.runToExact,
                    SleepAction(1.0),
                    Positions.End.runToExact,
                    SleepAction(1.0),
                )
            )
        )
    }
}
