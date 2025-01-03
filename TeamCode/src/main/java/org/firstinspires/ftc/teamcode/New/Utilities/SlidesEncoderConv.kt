package org.firstinspires.ftc.teamcode.New.Utilities
//note circumference is inputted as mm
class SlidesEncoderConv(var circumference: Double) {

    val ticksPerRev: Double = 28.0
    val inPerMm: Double = 0.0393701

    fun toIn(ticks: Double): Double {
        return (ticks / ticksPerRev) * circumference * inPerMm
    }

}