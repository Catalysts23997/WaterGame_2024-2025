package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

class LinearSlides(private val hwMap:HardwareMap) {

    private val leftMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "leftSlide")
    private val rightMotor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "rightMotor")
    lateinit var slidesState: SlidesState


    //todo: Create an opmode that uses this subsytem - and sets the pid controller's paramters to ftc Dashboard numbers

    private val pidController = Controller(PIDParams(0.0,0.0,0.0,0.0))


    //NOTE: Max encoder ticks is 311, otherwise we break hardware

    private val circumferenceOfSpool: Double = 24 * Math.PI
    var conv: SlidesEncoderConv = SlidesEncoderConv(circumferenceOfSpool)

    fun update(){
        //in to tick
        val target = slidesState.distance
        val effort = pidController.calculate(target)
        leftMotor.power = effort
        rightMotor.power = -effort
    }

    private fun InToTick(input: Double): Double {
        return 0.0
    }


    enum class SlidesState(Distance: Double) {
        WALL(0.0),
        INTAKE(10.0),
        BAR(15.0),
        HANG(20.0);

        val distance = Distance
    }

}