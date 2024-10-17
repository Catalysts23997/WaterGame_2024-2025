package org.firstinspires.ftc.teamcode.New.Opmodes.Actions

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Actions.Testing
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ShoudlerJohn
import com.acmerobotics.roadrunner.ftc.runBlocking;


class ActionsExamples: LinearOpMode() {

    override fun runOpMode() {
        val testing = Testing(ShoudlerJohn(hardwareMap))
        waitForStart();
        while (opModeIsActive() && !isStopRequested){

            val packet = TelemetryPacket()
            testing.run(packet)
            runBlocking(
                SequentialAction(
                    testing,
                    testing




                )
            )
        }


    }


}