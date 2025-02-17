package org.firstinspires.ftc.teamcode.New.KeepForFuture.Tele.Testing//package org.firstinspires.ftc.teamcode.Kotlin_Bromine_Arya.Opmodes.`Testing$Tuning`.Auto.Localizer

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.KeepForFuture.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Poses

@TeleOp(name = "DrivingPractice", group = "Linear OpMode")
class DriveTest : LinearOpMode(){
    override fun runOpMode() {
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().telemetry)

        val localizer = Localizer(hardwareMap, Poses(0.0, 0.0, 0.0))
        val drive = Drivetrain(hardwareMap)

        waitForStart()
        while (opModeIsActive() && !isStopRequested) {
            localizer.update()

            if(gamepad1.a){
                localizer.resetHeading()
            }

            drive.update(arrayListOf(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x))

            telemetry.addData("x", gamepad1.left_stick_x)
            telemetry.addData("y", gamepad1.left_stick_y)

            telemetry.addData("Heading", Localizer.pose.heading)
            telemetry.addData("X position", Localizer.pose.x)
            telemetry.addData("Y position", Localizer.pose.y)
            telemetry.update()
        }
    }
}