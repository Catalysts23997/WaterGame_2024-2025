package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.ReleasingActions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExactForever
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.T
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@Autonomous(name = "PushYellowAuto", group = "Auto")
class PushYellowAuto : LinearOpMode() {

    companion object{
        var rT = Poses(-19.0,-66.0,0.0)
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry
        TiegerAuto.rT = Poses(-19.0,-66.0,0.0)

        val localizer = Localizer(hardwareMap, TiegerAuto.rT)
        val drive = Drivetrain(hardwareMap)
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
                    Positions.Pos1Y.runToExact,
                    Positions.GotoSample1Y.runToExact,
                    Positions.PushtoSample1Y.runToExact,
                    Positions.GotoSample1Y.runToExact,
                    Positions.GotoSample2Y.runToExact,
                    Positions.PushtoSample2Y.runToExact,
                    Positions.GotoSample2Y.runToExact,
                    Positions.GotoSample3Y.runToExact,
                    Positions.PushtoSample3Y.runToExact,
                    Positions.GotoSample1Y.runToExact,
                    Positions.YEnd.runToExact

                )
            )
        )

    }
}
