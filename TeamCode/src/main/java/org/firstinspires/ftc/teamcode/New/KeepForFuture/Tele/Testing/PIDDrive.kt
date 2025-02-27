package org.firstinspires.ftc.teamcode.New.KeepForFuture.Tele.Testing

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.KeepForFuture.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Poses

/**
 * var paTerm = 0.2
 *
 *         @JvmField
 *         var iaTerm = 0.0001
 *
 *         @JvmField
 *         var daTerm = 0.018
 *
 *         @JvmField
 *         var pTerm = 0.2
 *
 *         @JvmField
 *         var iTerm = 0.0001
 *
 *         @JvmField
 *         var dTerm = 0.02
 *
 *         @JvmField
 *         var spTerm = 1.2
 *
 *         @JvmField
 *         var siTerm = 0.0001
 *
 *         @JvmField
 *         var sdTerm = 0.1
 */
@Config
@Disabled
@TeleOp(name = "TuningPIDd", group = "Linear OpMode")
class PIDDrive : LinearOpMode() {
    companion object {
        @JvmField
        var paTerm = 0.0

        @JvmField
        var iaTerm = 0.0

        @JvmField
        var daTerm = 0.0

        @JvmField
        var pTerm = 0.0

        @JvmField
        var iTerm = 0.0

        @JvmField
        var dTerm = 0.0

        @JvmField
        var spTerm = 0.0

        @JvmField
        var siTerm = 0.0

        @JvmField
        var sdTerm = 0.0
    }

    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

        val localizer = Localizer(hardwareMap, Poses(0.0, 0.0, 0.0))
        val drive = Drivetrain(hardwareMap)

        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    Drivetrain.instance.Xpid.setPID(paTerm, iaTerm, daTerm,0.0)
                    Drivetrain.instance.Xpid.setPID(pTerm, iTerm, dTerm,0.0)
                    Drivetrain.instance.Xpid.setPID(spTerm, siTerm, sdTerm,0.0)
                    telemetry.addData("x", Localizer.pose.x)
                    telemetry.addData("y", Localizer.pose.y)
                    telemetry.update()
                    true
                },

//                SequentialAction(
//                    Positions.Test.runToExact,
//                    Positions.Test2.runToExact,
//                    Positions.Test.runToExact,
//                    Positions.Test2.runToExact,
//                    Positions.Test.runToExact,
//                    Positions.Test2.runToExact,
//                    Positions.Test.runToExact,
//                    Positions.Test2.runToExact,
//                    Positions.Test.runToExact,
//                    Positions.Test2.runToExact,
//                )
            )
        )
    }
}
