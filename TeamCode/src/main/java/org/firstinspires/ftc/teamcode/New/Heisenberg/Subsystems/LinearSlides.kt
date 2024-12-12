package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Controller
import org.firstinspires.ftc.teamcode.New.PIDParams

class LinearSlides(private val hwMap:HardwareMap) {

    private val leftMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "leftSlide")
    private val rightMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "rightMotor")


    //todo: Create an opmode that uses this subsytem - and sets the pid controller's paramters to ftc Dashboard numbers

    private val pidController = Controller(PIDParams(0.0,0.0,0.0,0.0))

    fun setPID(p: Double, i: Double, d: Double, f: Double) {
        pidController.params = PIDParams(p,i,d,f)
    }
    enum class State(val encoder: Int) {
        HANG(5000),
        BASKET(430),
        CLIP(340),
        INTAKE(550),
        STATIONARY(220),
        IDLE(0),
        Manual(0)
    }

    var state = State.IDLE

    fun update(){
        val effort = pidController.calculate(state.encoder.toDouble())
        leftMotor.power = effort
        rightMotor.power = -effort
    }

}