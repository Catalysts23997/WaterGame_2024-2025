package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.PID

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

@Config
@TeleOp(group = "Linear OpMode", name = "PidTest- Slides")
class AnyMotor: LinearOpMode() {

    companion object{

        @JvmField var p = 0.0
        @JvmField var i = 0.0
        @JvmField var d = 0.0
        @JvmField var f = 0.0

        @JvmField var target = 0.0 //inches
    }

    override fun runOpMode() {

        val pidController = Controller(PIDParams(0.0,0.0,0.0,0.0))
        val left = hardwareMap.get(DcMotorEx::class.java, "leftSlide")
        val right = hardwareMap.get(DcMotorEx::class.java, "rightSlide")

        left.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        right.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        left.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        right.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        val calc = SlidesEncoderConv(24*Math.PI)

        while (opModeIsActive()){

            pidController.setPID(p,i,d,f)
            pidController.calculate(target)

            telemetry.addData("Position  - Left", calc.toIn(left.currentPosition))
            telemetry.addData("Position  - Left", calc.toIn(right.currentPosition))

            telemetry.addData("Position  - Left", target)

            telemetry.update()

        }
    }

}