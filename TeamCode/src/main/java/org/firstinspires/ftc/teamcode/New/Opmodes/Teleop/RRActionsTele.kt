package org.firstinspires.ftc.teamcode.New.Opmodes.Teleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.SequentialAction
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Drive

//todo test after getting wheels in right directions
@TeleOp(name = "Teleop", group = "Linear OpMode")
class RRActionsTele : LinearOpMode() {

    override fun runOpMode() {
        val dash: FtcDashboard = FtcDashboard.getInstance()
        val packet = TelemetryPacket()
        var runningActions = ArrayList<Action>()

        val bromine = Bromine(hardwareMap)
        val timer = ElapsedTime()

        val drive = Drive(hardwareMap)
        while (opModeInInit()) timer.reset()

        while (opModeIsActive()) {

            // actions you are running

            if (gamepad2.a) {
                runningActions.add(
                    SequentialAction(bromine.prepareSpecimenWallIntake, bromine.SpecimenWallIntake)
                )
            }
            if (gamepad2.y) {
                runningActions.add(bromine.prepareSpecimenDeposit)
            }
            if (gamepad2.x) {
                runningActions.add(bromine.fullSpecimenDeposit)
            }
            if (gamepad2.right_bumper) {
                runningActions.add(bromine.prepareSampleIntake)
            }
            if (gamepad2.right_trigger > 0.5) {
                runningActions.add(bromine.SampleIntake)
            }
            if (gamepad2.left_bumper) {
                runningActions.add(SequentialAction(bromine.prepForHPdrop, bromine.Drop))
            }
            if (gamepad2.left_trigger > 0.5) {
                runningActions.add(bromine.Drop)
            }
            if (gamepad2.dpad_up) {
                runningActions.add(bromine.prepareBasketDeposit)
            }


            // update running actions
            val newActions = ArrayList<Action>()
            runningActions.forEach {
                it.preview(packet.fieldOverlay())
                if (it.run(packet)) {
                    newActions.add(it)
                }
            }
            runningActions = newActions

            dash.sendTelemetryPacket(packet)

            //update subsystems
            bromine.teleUpdate(gamepad1)
            drive.update(
                arrayListOf(
                    gamepad1.left_stick_x,
                    gamepad1.left_stick_y,
                    gamepad1.right_stick_x
                )
            )
            timer.reset()
        }
    }
}
