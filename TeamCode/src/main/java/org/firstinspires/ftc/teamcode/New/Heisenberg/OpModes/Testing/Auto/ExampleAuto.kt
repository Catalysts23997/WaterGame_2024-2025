package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Auto

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

class ExampleAuto : LinearOpMode() {

    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))

        waitForStart()

        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    false
                },
                SequentialAction(
//                    Positions.Rbasket.runToNearest,
//                    Positions.RRbrick1.runToNearest,
//                    Positions.Rbasket.runToNearest,
//                    Positions.RRbrick2.runToNearest,
//                    Positions.Rbasket.runToNearest,
//                    Positions.RRbrick3.runToNearest
                )
            )
        )

    }


}
