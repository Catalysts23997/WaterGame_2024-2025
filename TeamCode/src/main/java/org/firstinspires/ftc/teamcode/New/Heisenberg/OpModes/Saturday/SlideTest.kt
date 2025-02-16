package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides
@Disabled
@TeleOp(group = "Linear OpMode", name = "SlideTest")
class SlideTest : LinearOpMode() {

    companion object {
        @JvmField
        var motorpower = 0.0
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry

        val slides = LinearSlides(hardwareMap)

        waitForStart()
        while (opModeIsActive()) {
            slides.motor.power = motorpower
            telemetry.addData("Amps", slides.motor.getCurrent(CurrentUnit.AMPS))
            telemetry.update()
        }
    }

}