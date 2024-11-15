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

        val localizer = Localizer(hardwareMap, Pose2d(0.0, 0.0, 0.0))
        val subsystems = Bromine(hardwareMap)

        while (opModeIsActive()) {

//            runBlocking(
//                ParallelAction(
//                    Action {
//                        localizer.update()
//                        false },
//                    SequentialAction(
//                        ParallelAction(Positions.RRbrick1.runToNearest,subsystems.intake(true)),
//                        subsystems.depositBasket(1.0),
//                        ParallelAction(Positions.RRbrick2.runToNearest,subsystems.intake(true)),
//                        subsystems.depositBasket(1.0),
//                        ParallelAction(Positions.RRbrick3.runToNearest,subsystems.intake(true)),
//                        subsystems.depositBasket(1.0),
//                        Positions.Rzone.runToExact
//                    )
//                )
//            )

        }


    }
}