package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.SubSystems
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams

class Linkage(hardwareMap: HardwareMap): SubSystems {
    private val motor: DcMotorEx = hardwareMap.get(DcMotorEx::class.java,"linkageMotor")

    private val controller = Controller(PIDParams(1.8089531093740894, 0.15506403605488558, 0.15316684843308717, 0.23084969258387814))
    enum class State(val angle: Double){
        Horizontal(0.0),
        Basket(70.0),
        SubmersibleStart(10.0);
    }
    override var state = State.Basket

    override fun update() {
        val currentAngle = 0.0

        val target = state.angle
        val pidParams = if(currentAngle>target){
            PIDParams(1.8089531093740894, 0.15506403605488558,
                0.16316684843308718, 0.23084969258387814)
        } else{
            PIDParams(1.8089531093740894, 0.15506403605488558, 0.15316684843308717, 0.23084969258387814)
        }

        controller.setPID(pidParams)
        motor.power  = controller.calculate(target,currentAngle*Math.PI/180.0)

    }
}