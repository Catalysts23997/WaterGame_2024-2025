package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

@Config
@TeleOp(group = "Linear OpMode", name = "PidTest- Slides")
class SlideRotator: LinearOpMode() {

    companion object{

        @JvmField var p = 0.0
        @JvmField var i = 0.0
        @JvmField var d = 0.0
        @JvmField var f = 0.0

        @JvmField var target = 0.0 //inches
    }

    override fun runOpMode() {

        val pidController = Controller(PIDParams(0.0,0.0,0.0,0.0))
        val motor = hardwareMap.get(DcMotorEx::class.java, "linkage")

        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        val calc = SlidesEncoderConv(24*Math.PI)

        waitForStart()
        while (opModeIsActive()){

            val currentAngle  = (((motor.currentPosition*2*11/12)/1425.05923061 * 2 * Math.PI) *180/ Math.PI)/4 + 90

            pidController.setPID(p, i, d, f)

//            telemetry.addData("Position  - Right", calc.toIn(motor.currentPosition))
            telemetry.addData("CurrentPose", currentAngle * Math.PI/180)
            motor.power  =  pidController.calculate(target -(currentAngle * Math.PI/180),currentAngle * Math.PI/180)

            telemetry.update()
        }
    }

}