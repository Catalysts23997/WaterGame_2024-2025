package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.SetDriveTarget
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.T
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@TeleOp(name = "Basket Auto", group = "Linear OpMode")
class BasketAuto : LinearOpMode() {

    companion object{
        var reT = Poses(-19.0,-72.0,0.0)
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry

//        val localizer = Localizer(hardwareMap, Poses(30.5, -57.0, 0.0))
        val localizer = Localizer(hardwareMap, reT)
        val drive = Drive(hardwareMap)

        T.autoType = true

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
                    SleepAction(1.0),
                    Positions.Yellow1.runToExact,
                    SleepAction(1.0),
                    Positions.Basket.runToExact,
                    SleepAction(1.0),
                    Positions.Yellow2.runToExact,
                    SleepAction(1.0),
                    Positions.Basket.runToExact,
                    SleepAction(1.0),
                    Positions.Yellow3.runToExact,
                    SleepAction(1.0),
                    Positions.Basket.runToExact,
                    SleepAction(1.0),
                    Positions.End.runToExact,

                    )
            )
        )
    }
}
