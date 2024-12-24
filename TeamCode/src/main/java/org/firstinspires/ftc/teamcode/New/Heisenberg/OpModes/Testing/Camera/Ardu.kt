package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Camera

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AprilTagData
import org.firstinspires.ftc.teamcode.New.Poses

@Disabled
@TeleOp(name = "ArduCamAprilTags",group = "Linear OpMode")
class Ardu : LinearOpMode() {

    override fun runOpMode() {
        val localizer = Localizer(hardwareMap, Poses(0.0, 0.0, 0.0))
        val aprilTagProcessor = AprilTagData(hardwareMap)
waitForStart()
        while (opModeIsActive()){
            localizer.update()
            val pose = aprilTagProcessor.searchForTag()

            telemetry.addData("Pose x", pose.x)
            telemetry.addData("Pose y", pose.y)
        }
    }


}