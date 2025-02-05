package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit
import org.firstinspires.ftc.teamcode.New.Utilities.Controller
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams

class LinearSlides(hwMap:HardwareMap) {

    val motor: DcMotorEx = hwMap.get(DcMotorEx::class.java, "slide")
    init {
        motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motor.mode  = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor.direction = DcMotorSimple.Direction.REVERSE
    }

    //todo: Create an opmode that uses this subsytem - and sets the pid controller's paramters to ftc Dashboard numbers

    private val pidController = Controller(PIDParams(.007,0.0,0.001,0.0))


    //NOTE: Max encoder ticks is 311, otherwise we break hardware

//    private val circumferenceOfSpool: Double = 24 * Math.PI
//    var conv: SlidesEncoderConv = SlidesEncoderConv(circumferenceOfSpool)

    var state = SlidesState.IDLE
    private var offset = 0
    private var currentPos =0

    fun update(){
        //in to tick
        if(motor.getCurrent(CurrentUnit.AMPS) >= 9.0 && currentPos <= 700) offset+= motor.currentPosition
        currentPos = motor.currentPosition - offset
        motor.power = pidController.calculate(state.distance -currentPos)
    }

//    private fun InToTick(input: Double): Double {
//        return 0.0
//    }


    enum class SlidesState(val distance: Double) {
        WALL(1100.0),
        INTAKE(1100.0),
        BASKET(1200.0),
        Specimen(800.0),
        SpecimenHang(600.0),
        IDLE(0.0),
        HANG(800.0);
    }

}