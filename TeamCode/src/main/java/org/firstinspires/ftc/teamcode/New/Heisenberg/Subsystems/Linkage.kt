package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday.SlideRotator
import org.firstinspires.ftc.teamcode.New.SubSystems
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams

class Linkage(hardwareMap: HardwareMap): SubSystems {
    val motor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java,"linkage")


    private val controller = Controller(PIDParams(1.0, 0.0, .001, 0.2))
    enum class State(val angle: Double){
        Horizontal(0.0),
        Basket(Math.PI/2),
        SubmersibleStart(Math.PI/16);
    }


    override var state = State.Horizontal

    fun reset() {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    override fun update() {
        val currentAngle  = (Math.PI /180) * ((((motor.currentPosition*2*11/12)/1425.05923061 * 2 * Math.PI) *180/ Math.PI)/4 + 90)

        motor.power  =  controller.calculate((state.angle* Math.PI/180 -currentAngle),currentAngle)
    }


}
