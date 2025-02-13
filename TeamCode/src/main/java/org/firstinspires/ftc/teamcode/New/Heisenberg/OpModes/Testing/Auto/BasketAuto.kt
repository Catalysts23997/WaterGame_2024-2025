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

@TeleOp(name = "Basket Auto", group = "Linear OpMode")
class BasketAuto : LinearOpMode() {
    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

//        val localizer = Localizer(hardwareMap, Poses(30.5, -57.0, 0.0))
        val drive = Drive(hardwareMap)
        val localizer = Localizer(hardwareMap, Poses(-19.0,-72.0,0.0))

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

                    Positions.Basket.runToExact,
                    Positions.Yellow1.runToExact,
                    Positions.Basket.runToExact,
                    Positions.Yellow2.runToExact,
                    Positions.Basket.runToExact,
                    Positions.Yellow3.runToExact,
                    Positions.Basket.runToExact,
                    Positions.End.runToExact,

                    )
            )
        )
    }
}
