package org.firstinspires.ftc.teamcode.New.Old_Examples.OpModes.Teleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.ReleasingActions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

//todo test after getting wheels in right directions
@TeleOp(name = "ActionTests", group = "Linear OpMode")
class Tests : LinearOpMode() {

    override fun runOpMode() {
        val dash: FtcDashboard = FtcDashboard.getInstance()
        val packet = TelemetryPacket()
        var runningActions = ArrayList<Action>()

        val robot = ReleasingActions(hardwareMap)
        val timer = ElapsedTime()

        val drive = Drivetrain(hardwareMap)
        val localizer = Localizer(hardwareMap, Poses(0.0, 0.0, 0.0))

        while (opModeInInit()) timer.reset()

        while (opModeIsActive()) {

            if(gamepad2.left_trigger >= .5){
                runningActions.add(
                    robot.Release
                )
            }
//            robot.SpecimenHang
            if(gamepad2.right_trigger >= .5){
                runningActions.add(
                    SequentialAction(
                        robot.GrabPre, SleepAction(1.3), robot.Grab
                    )
                )
            }

            if(gamepad2.a){
                runningActions.add(
                    robot.ForwardIntake
                )
            }
//            if(gamepad2.b){
//                runningActions.add(
//                    SequentialAction(
//                        robot.HPdrop,
//                        SleepAction(1.0),
//                        robot.WallGrab
//                    )
//                )
//            }
//            if(gamepad2.y){
//                runningActions.add(
//                    robot.SpecimenHangPrep
//                )
//            }
//            if(gamepad2.x){
//                runningActions.add(
//                    robot.SpecimenHang
//                )
//            }
            if(gamepad2.y){
                runningActions.add(
                    robot.Basket
                )
            }
//            if(gamepad2.left_bumper){
//                runningActions.add(
//                    robot.BackIntake
//                )
//            }
            if(gamepad2.x){
                runningActions.add(
                   robot.Retract
                )
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

            localizer.update()

            //update subsystems
            robot.update(gamepad2.left_stick_y.toDouble())
            drive.update(
                arrayListOf(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    gamepad1.right_stick_x
                )
            )
            if(gamepad1.a){
                localizer.resetHeading()
            }
            timer.reset()
        }
    }
}
