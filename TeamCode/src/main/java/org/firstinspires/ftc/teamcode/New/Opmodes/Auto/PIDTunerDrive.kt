package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.Competition.Actions.PIDdrive
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

@Config
@TeleOp
class PIDTunerDrive : LinearOpMode() {
    companion object {
        var pTerm = doubleArrayOf(0.0, 0.0, 0.0)
        var iTerm = doubleArrayOf(0.0, 0.0, 0.0)
        var dTerm = doubleArrayOf(0.0, 0.0, 0.0)
    }

    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))
        val drive = PIDdrive(hardwareMap)

       runBlocking(
                ParallelAction(
                    Action {
                        localizer.update()
                        drive.setPID(pTerm, iTerm, dTerm)
                       true },
                    SequentialAction(
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                        Positions.YellowLeftbrick1.runToExact,
                        Positions.YellowLeftbrick3.runToExact,
                    )
                )
            )


    }
}