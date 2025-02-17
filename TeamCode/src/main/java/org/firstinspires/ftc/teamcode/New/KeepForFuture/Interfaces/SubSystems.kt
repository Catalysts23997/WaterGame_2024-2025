package org.firstinspires.ftc.teamcode.New.KeepForFuture.Interfaces

interface SubSystems {
    fun update(){}
    val state: Any
        get() ={}
    fun update(gamepadInput: ArrayList<Float>){}
}