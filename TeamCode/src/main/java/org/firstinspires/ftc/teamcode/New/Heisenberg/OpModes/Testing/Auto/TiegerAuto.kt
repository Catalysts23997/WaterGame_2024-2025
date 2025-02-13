package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@TeleOp(name = "TiegerAuto", group = "Linear OpMode")
class TiegerAuto : LinearOpMode() {
    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

//        val localizer = Localizer(hardwareMap, Poses(30.5, -57.0, 0.0))
        val drive = Drive(hardwareMap)
        val localizer = Localizer(hardwareMap, Poses(19.0,-72.0,0.0))

        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    telemetry.addData("x", Localizer.pose.x)
                    telemetry.addData("y", Localizer.pose.y)
                    telemetry.update()
                    true
                },
                SequentialAction(

                    Positions.RedSpecimin1.runToExact,
                    Positions.RedSample1.runToExact,
                    Positions.RedPlayer1.runToExact,
                    Positions.RedSample2.runToExact,
                    Positions.RedPlayer2.runToExact,
                    Positions.RedSample3.runToExact,
                    Positions.RedPlayer3.runToExact,
                    Positions.RedWallIntake.runToExact,
                    Positions.RedSpecimin2.runToExact,
                    Positions.RedWallIntake.runToExact,
                    Positions.RedSpecimin3.runToExact,
                    Positions.RedWallIntake.runToExact,
                    Positions.RedSpecimin4.runToExact,
                    Positions.RedWallIntake.runToExact,
                    Positions.RedSpecimin5.runToExact,





                )
            )
        )
    }
}
