package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes.Camera

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AprilTagData

@TeleOp(name = "ArduCamAprilTags",group = "Linear OpMode")
class Ardu : LinearOpMode() {

    override fun runOpMode() {
        val localizer = Localizer(hardwareMap, Pose2d(0.0,0.0,0.0))
        val aprilTagProcessor = AprilTagData(hardwareMap)

        while (opModeIsActive()){
            localizer.update()
            val pose = aprilTagProcessor.searchForTag()

            telemetry.addData("Pose x", pose.x)
            telemetry.addData("Pose y", pose.y)
        }
    }


}