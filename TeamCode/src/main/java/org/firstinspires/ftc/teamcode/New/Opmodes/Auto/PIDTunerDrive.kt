package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

@Config
@TeleOp(name = "TuningPIDd", group = "Linear OpMode")
class PIDTunerDrive : LinearOpMode() {
    companion object {
        @JvmField
        var paTerm = 0.2

        @JvmField
        var iaTerm = 0.0001

        @JvmField
        var daTerm = 0.018

        @JvmField
        var pTerm = 0.2

        @JvmField
        var iTerm = 0.0001

        @JvmField
        var dTerm = 0.02

        @JvmField
        var spTerm = 1.2

        @JvmField
        var siTerm = 0.0001

        @JvmField
        var sdTerm = 0.1
    }
    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))
        val drive = Drive(hardwareMap)

        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
//                    drive.setPID(
//                        doubleArrayOf(paTerm, pTerm, spTerm),
//                        doubleArrayOf(iaTerm, iTerm, siTerm),
//                        doubleArrayOf(daTerm, dTerm, sdTerm)
//                    )
                    telemetry.addData("x", Localizer.pose.x)
                    telemetry.addData("y", Localizer.pose.y)
                    telemetry.update()
                    true
                },
                SequentialAction(
                    Positions.Test.runToExact,
                    Positions.Test2.runToExact,
                    Positions.Test3.runToExact,
                    Positions.Test4.runToExact,
                    Positions.Test5.runToNearest
                )
            )
        )
    }
}
