package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

class LinearSlides(private val hwMap:HardwareMap) {

    private val leftMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "leftSlide")
    private val rightMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "rightMotor")


    //todo: Create an opmode that uses this subsytem - and sets the pid controller's paramters to ftc Dashboard numbers

    private val pidController = Controller(PIDParams(0.0,0.0,0.0,0.0))

    fun setPID(p: Double, i: Double, d: Double, f: Double) {
        pidController.params = PIDParams(p,i,d,f)
    }

    //NOTE: Max encoder ticks is 311, otherwise we break hardware, lets

    private val circumferenceOfSpool: Double = 24 * Math.PI
    var conv: SlidesEncoderConv = SlidesEncoderConv(circumferenceOfSpool)

    fun update(input: Double){
        //in to tick
        val target = if(input != 0.0) 0.0 else 0.0
        val effort = pidController.calculate(target)
        leftMotor.power = effort
        rightMotor.power = -effort
    }

}