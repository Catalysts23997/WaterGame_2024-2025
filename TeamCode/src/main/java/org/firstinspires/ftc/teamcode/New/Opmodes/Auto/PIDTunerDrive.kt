package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

@Config
class PIDTunerDrive : LinearOpMode() {
    companion object {
        var pTerm = doubleArrayOf(0.0, 0.0, 0.0)
        var iTerm = doubleArrayOf(0.0, 0.0, 0.0)
        var dTerm = doubleArrayOf(0.0, 0.0, 0.0)
    }

    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))

        while (opModeIsActive()) {

            runBlocking(
                ParallelAction(
                    Action {
                        localizer.update()
                       true },
                    SequentialAction(
//                        Positions.YLbrick1.runToExact,
//                        Positions.YLbrick3.runToExact,
//                        Positions.YLbrick1.runToExact,
//                        Positions.YLbrick3.runToExact,
//                        Positions.YLbrick1.runToExact,
//                        Positions.YLbrick3.runToExact,
//                        Positions.YLbrick1.runToExact,
//                        Positions.YLbrick3.runToExact,
                    )
                )
            )

        }


    }
}