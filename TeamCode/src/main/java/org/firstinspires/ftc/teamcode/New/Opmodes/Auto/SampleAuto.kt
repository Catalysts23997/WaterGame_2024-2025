package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

class SampleAuto : LinearOpMode() {

    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))
        val bromine = Bromine(hardwareMap);
        waitForStart()

        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    bromine.updateAuto()
                    true
                },
                SequentialAction(
                    bromine.prepareSpecimenDeposit,
                    bromine.fullSpecimenDeposit,

                    //grabbing the specimens and giving them to Human PLayer
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine. prepForHPdrop ,
                    bromine.Drop,
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine.prepForHPdrop ,
                    bromine.Drop,
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine.prepForHPdrop,
                    bromine.Drop,

                    //wall intakes and deposits
                    bromine.prepareSpecimenWallIntake,
                    bromine.SpecimenWallIntake,
                    bromine.prepareSpecimenDeposit,
                    bromine.fullSpecimenDeposit,
                    bromine.prepareSpecimenWallIntake,
                    bromine.SpecimenWallIntake,
                    bromine.prepareSpecimenDeposit,
                    bromine.fullSpecimenDeposit,
                    bromine.prepareSpecimenWallIntake,
                    bromine.SpecimenWallIntake,
                    bromine.prepareSpecimenDeposit,
                    bromine.fullSpecimenDeposit,
                )
            )
        )

    }


}
