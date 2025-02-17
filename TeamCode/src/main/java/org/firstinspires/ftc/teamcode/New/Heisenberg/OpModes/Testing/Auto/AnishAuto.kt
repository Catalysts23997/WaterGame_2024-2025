package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@Disabled
@TeleOp(name = "TiegerAuto", group = "Linear OpMode")
class AnishAuto : LinearOpMode() {
    override fun runOpMode() {
        val telemetry = FtcDashboard.getInstance().telemetry

        val localizer = Localizer(hardwareMap, Poses(30.5, -57.0, 0.0))
        val drive = Drivetrain(hardwareMap)

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

//                    Positions.Bluebasket.runToNearest,
//                    Positions.Blueleftbrick1.runToExact,
//                    Positions.Bluebasket.runToNearest,
//                    Positions.Blueleftbrick2.runToNearest,
//                    Positions.Bluebasket.runToNearest,
//                    Positions.Blueleftbrick3.runToNearest,
//                    Positions.Bluebasket.runToNearest,
//                    Positions.BackLeftSubmersableIntake.runToNearest,
                )
            )
        )
    }
}
