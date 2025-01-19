package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday.Slides
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams
import org.firstinspires.ftc.teamcode.New.Utilities.SlidesEncoderConv

class LinearSlides(hwMap:HardwareMap) {

    val motor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "slide")
    init {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    //todo: Create an opmode that uses this subsytem - and sets the pid controller's paramters to ftc Dashboard numbers

    private val pidController = Controller(PIDParams(0.008,0.0,0.001,0.0))


    //NOTE: Max encoder ticks is 311, otherwise we break hardware

//    private val circumferenceOfSpool: Double = 24 * Math.PI
//    var conv: SlidesEncoderConv = SlidesEncoderConv(circumferenceOfSpool)

    var state = SlidesState.IDLE
    private var offset = 0
    private var currentPos =0

    fun update(){
        //in to tick
        if(motor.getCurrent(CurrentUnit.AMPS) >= 7.0 && currentPos <= 700) offset+= motor.currentPosition
        currentPos = motor.currentPosition - offset
        motor.power = pidController.calculate(state.distance -currentPos)
    }

//    private fun InToTick(input: Double): Double {
//        return 0.0
//    }


    enum class SlidesState(val distance: Double) {
        WALL(1200.0),
        INTAKE(1400.0),
        BAR(1200.0),
        IDLE(0.0),
        HANG(1000.0);
    }

}