package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Claw
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ClawRotater
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ColorSensor
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.WristJohn
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer

class SampleAuto : LinearOpMode() {

    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, Pose2d(0.0, 0.0, 0.0))
        val bromine = Bromine(hardwareMap);
        waitForStart()

        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    false
                },
                SequentialAction(
                    bromine.prepareSpecimenDeposit,
                    bromine.fullSpecimenDeposit,

                    //grabbing the specimens and giving them to Human PLayer
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine. prepForHPdrop ,
                    bromine.HPdrop,
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine.prepForHPdrop ,
                    bromine.HPdrop,
                    bromine.prepareSampleIntake,
                    bromine.SampleIntake,
                    bromine.prepForHPdrop,
                    bromine.HPdrop,

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


                    Positions.Rbasket.runToNearest,
                    Positions.RRbrick1.runToNearest,
                    Positions.Rbasket.runToNearest,
                    Positions.RRbrick2.runToNearest,
                    Positions.Rbasket.runToNearest,
                    Positions.RRbrick3.runToNearest
                )
            )
        )

    }


}
