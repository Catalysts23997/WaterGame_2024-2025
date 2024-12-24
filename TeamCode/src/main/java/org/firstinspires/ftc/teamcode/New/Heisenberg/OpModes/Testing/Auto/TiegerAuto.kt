package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Poses

@TeleOp(name = "TiegerAuto", group = "Linear OpMode")
class TiegerAuto : LinearOpMode() {
    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

        val localizer = Localizer(hardwareMap, Poses(30.5, -57.0, 0.0))
        val drive = Drive(hardwareMap)

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

                    Positions.RedSpecieminBar.runToNearest,
                    Positions.RedBrickMiddle.runToExact,
                    Positions.RedBrickMiddleLeft.runToExact,
                    Positions.RedBrickMiddle.runToExact,
                    Positions.RedBrickMiddleRight.runToExact,
                    Positions.RedBrickMiddle.runToExact,
                    Positions.CornerOfRedZone.runToNearest,
                    Positions.RedSpecieminBar.runToNearest,
                    Positions.CornerOfRedZone.runToNearest,
                    Positions.RedSpecieminBar.runToNearest,
                    Positions.CornerOfRedZone.runToNearest,
                    Positions.RedSpecieminBar.runToNearest,
                    Positions.RedHumanIntake.runToExact

                )
            )
        )
    }
}
