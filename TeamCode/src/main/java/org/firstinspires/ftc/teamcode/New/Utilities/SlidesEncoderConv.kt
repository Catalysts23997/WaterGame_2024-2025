package org.firstinspires.ftc.teamcode.New.Utilities
//note circumference is inputted as mm
class SlidesEncoderConv(private var circumference: Double) {

    private val ticksPerRev: Double = 28.0
    private val inPerMm: Double = 1.0/25.4

    fun toIn(ticks: Int): Double {
        return (ticks / ticksPerRev) * circumference * inPerMm
    }

}