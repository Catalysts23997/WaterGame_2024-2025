package org.firstinspires.ftc.teamcode.New

import com.qualcomm.robotcore.util.ElapsedTime


object Wait {
    val wait: ElapsedTime = ElapsedTime()
    private var runOnce = false

//    fun waitFor(timeInMs: Int) {
//        if (timeStamp(timeInMs)) {
//            Sequencer.MAJORCOMMAND++
//        }
//    }

    fun <Parameter> runAsynchActionAfter(
        timeInMs: Int,
        action: (Parameter) -> Unit,
        parameter: Parameter
    ) {
        if (timeStamp(timeInMs)) action(parameter)
    }

    fun runAsynchActionAfter(timeInMs: Int, action: () -> Unit) {
        if (timeStamp(timeInMs)) action()
    }

    private fun timeStamp(timeInMs: Int): Boolean {
        var timeStamp = 0.0
        if (!runOnce) {
            timeStamp = wait.milliseconds()
        }
        runOnce = true

        return if (timeInMs <= wait.milliseconds() - timeStamp) {
            runOnce = false
            true
        } else false
    }

}


