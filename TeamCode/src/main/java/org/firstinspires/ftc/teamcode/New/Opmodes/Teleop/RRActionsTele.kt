package org.firstinspires.ftc.teamcode.New.Opmodes.Teleop

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Future.Actions.AutoDriveToTag
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Drive
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AprilTagData

//todo test after getting wheels in right directions
class RRActionsTele : LinearOpMode() {

    override fun runOpMode() {
        val dash: FtcDashboard = FtcDashboard.getInstance()
        val packet = TelemetryPacket()
        var runningActions = ArrayList<Action>()

        val localizer = Localizer(hardwareMap, Pose2d(0.0,0.0,0.0))
        val drive = Drive(hardwareMap)
        val driveToTag = AutoDriveToTag(AprilTagData(hardwareMap),drive)

        while (opModeIsActive()) {
            localizer.update()

            // actions you are running

            if(gamepad1.a){
                runningActions.add(driveToTag)
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
            drive.update(arrayListOf(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x))

        }
    }
}
