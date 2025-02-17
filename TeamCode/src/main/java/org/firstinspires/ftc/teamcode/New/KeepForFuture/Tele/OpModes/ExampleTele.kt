package org.firstinspires.ftc.teamcode.New.Old_Examples.OpModes.Teleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime

import org.firstinspires.ftc.teamcode.New.KeepForFuture.Actions.ExampleActions
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.KeepForFuture.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Poses

//todo test after getting wheels in right directions
@TeleOp(name = "ExampleTele", group = "Linear OpMode")
class ExampleTele : LinearOpMode() {

    override fun runOpMode() {
        val dash: FtcDashboard = FtcDashboard.getInstance()
        val packet = TelemetryPacket()
        var runningActions = ArrayList<Action>()

        val robot = ExampleActions(hardwareMap)
        val timer = ElapsedTime()

        val drive = Drivetrain(hardwareMap)
        val localizer = Localizer(hardwareMap, Poses(0.0, 0.0, 0.0))

        while (opModeInInit()) timer.reset()

        while (opModeIsActive()) {

            if(gamepad2.left_trigger >= .5){
                runningActions.add(
                    robot.example
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
            robot.update()
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
