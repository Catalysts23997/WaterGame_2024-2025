package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Tele

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.Poses

@Autonomous(name = "CornerAutp", group = "Auto")
class CornerAutp : LinearOpMode(){


    override fun runOpMode() {
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)

        val localizer = Localizer(hardwareMap, Poses(0.0,0.0,0.0))
        val drive = Drive(hardwareMap)
        val timer= ElapsedTime()

        waitForStart()
        timer.reset()
        while (opModeIsActive() && !isStopRequested) {
            localizer.update()
            while (timer.seconds()<1.4555555){
                drive.update(arrayListOf(0.0F, 0.8F, 0F))
            }
            drive.update(arrayListOf(0F, 0F, 0F))
            stop()
        }
    }
}