package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Sensors

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Old.subsystems.Claw
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ColorSensor

@TeleOp(name = "ColorSensorTest", group = "Linear OpMode")
class ColorSensorTest : LinearOpMode() {

    override fun runOpMode() {

        val colorSensor = ColorSensor(hardwareMap)
        val claw = Claw(hardwareMap)

        waitForStart()
        while (opModeIsActive()) {
            claw.clawState = if (colorSensor.checkForRecognition()) Claw.ClawState.CLOSED else Claw.ClawState.OPEN
        }
    }


}