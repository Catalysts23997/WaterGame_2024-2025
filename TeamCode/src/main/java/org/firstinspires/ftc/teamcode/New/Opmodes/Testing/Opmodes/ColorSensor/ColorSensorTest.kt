package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.ColorSensor

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ColorSensor
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AprilTagData
@TeleOp(name = "ColorSensorTest",group = "Linear OpMode")
class ColorSensorTest : LinearOpMode() {

    override fun runOpMode() {

        val colorSensor = ColorSensor(hardwareMap)

        waitForStart()
        while (opModeIsActive()){

            telemetry.addData("ColorSensor Reading",colorSensor.checkForRecognition().toString())
            telemetry.update()
        }
    }


}