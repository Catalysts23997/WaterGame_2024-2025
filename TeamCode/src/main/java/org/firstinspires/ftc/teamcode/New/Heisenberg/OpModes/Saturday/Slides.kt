package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

@Config
@TeleOp(group = "Linear OpMode", name = "SlidesT")
class Slides: LinearOpMode() {

    companion object{

        @JvmField var p = 0.0
        @JvmField var i = 0.0
        @JvmField var d = 0.0
        @JvmField var f = 0.0

        @JvmField var target = 0.0 //inches
    }

    override fun runOpMode() {
        telemetry = FtcDashboard.getInstance().telemetry

        val pidController = Controller(PIDParams(0.0015,0.0,0.0015,0.0))
        val motor = hardwareMap.get(DcMotorEx::class.java, "slide")

        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        motor.direction = DcMotorSimple.Direction.REVERSE

//        val calc = SlidesEncoderConv(24*Math.PI)

        waitForStart()
        while (opModeIsActive()){

            val currentPos = motor.currentPosition
            pidController.setPID(p, i, d, f)

//            telemetry.addData("Position  - Right", calc.toIn(motor.currentPosition))
            telemetry.addData("CurrentPose", currentPos)
            motor.power  =  pidController.calculate(target -currentPos)

            telemetry.update()
        }
    }

}