package org.firstinspires.ftc.teamcode.New.Opmodes.Teleop

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.SubSystems.Actions.States


class ActionsExamples: LinearOpMode() {



    override fun runOpMode() {
        val actions = States(hardwareMap)

        waitForStart();
        while (opModeIsActive() && !isStopRequested){

            //runBlocking()

        }


    }


}