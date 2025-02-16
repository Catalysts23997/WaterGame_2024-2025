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

    init {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    //1.27 mult

    private val controller = Controller(PIDParams(3.5, 0.01, .27, 0.15))
    enum class State(val angle: Double){
        Horizontal(15.0 + 90),
        Basket(80.0 + 90),
        Vertical(90.0+90),
        Specimin(45.0 +90),
        SubmersibleStart(11.0 + 90);
    }

    val mult = 1.27


    override var state = State.Horizontal

    override fun update() {
        val currentAngle  = (Math.PI /180) * ((((motor.currentPosition*2*11/12)/1425.05923061 * 2 * Math.PI) *180/ Math.PI)/4 + 105)
        if(state == State.Horizontal) motor.power = 0.0
        else motor.power  =  controller.calculate((state.angle* Math.PI/180 * mult -currentAngle),currentAngle)
    }


}
