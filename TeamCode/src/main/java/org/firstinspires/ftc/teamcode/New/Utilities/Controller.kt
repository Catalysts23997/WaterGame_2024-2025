package org.firstinspires.ftc.teamcode.New.Utilities

import com.qualcomm.robotcore.util.ElapsedTime
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

class Controller(var params: PIDParams) {
    private var prevError = 0.0
    private var integral = 0.0
    private val timer = ElapsedTime()
    var pastTime = 0.0
    fun calculate(
        target: Double,
        armAngle: Double = 0.0
    ): Double {
        val dt = timer.seconds() - pastTime
        val error = target
        integral += (error * dt)

        val derivative = (error - prevError) / dt
        prevError = error

        val ff =
            if (params.kf != 0.0) if (armAngle < PI) max(0.0, sin(armAngle)) * params.kf else min(
                0.0,
                -sin(PI - (armAngle - PI))
            ) * params.kf else 0.0


        val controlEffort =
            ((derivative * params.kd + integral * params.ki + error * params.kp) + ff).coerceIn(
                -1.0,
                1.0
            )
        pastTime = timer.seconds()

//        Log.d("errorsss", ff.toString())
//        Log.d("errorsss", Math.toDegrees(armAngle).toString())
        return controlEffort
    }

    fun setPID(p: Double, i: Double, d: Double, f: Double) {
        params = PIDParams(p,i,d,f)
    }
}
