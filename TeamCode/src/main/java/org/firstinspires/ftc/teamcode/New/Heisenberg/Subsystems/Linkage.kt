package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday.SlideRotator
import org.firstinspires.ftc.teamcode.New.SubSystems
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams

class Linkage(hardwareMap: HardwareMap): SubSystems {
    val motor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java,"linkage")

    private val controller = Controller(PIDParams(1.0, 0.0, .001, 0.2))
    enum class State(val angle: Double){
        Horizontal(5.0),
        Basket(80.0),
        SubmersibleStart(11.0);
    }

    override var state = State.Basket

    override fun update() {

        val currentAngle  = (((motor.currentPosition*2*11/12)/1425.05923061 * 2 * Math.PI) *180/ Math.PI)/4 + 90
        motor.power  =  controller.calculate(state.angle -(currentAngle * Math.PI/180),currentAngle * Math.PI/180)
    }


}
