package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Tele

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.Utilities.Poses
@Disabled
@TeleOp(name = "Comp2", group = "Linear OpMode")
class Comp2 : LinearOpMode(){
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