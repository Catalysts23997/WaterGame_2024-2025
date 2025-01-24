package org.firstinspires.ftc.teamcode.New.Utilities

import com.acmerobotics.roadrunner.Rotation2d
import com.acmerobotics.roadrunner.Vector2d
data class Poses(val x: Double, val y: Double, val heading: Double){
    constructor(vector2d: Vector2d, rotation: Double) : this(vector2d.x,vector2d.y, rotation)
    constructor(vector2d: Vector2d, rotation: Rotation2d) : this(vector2d.x,vector2d.y, rotation.toDouble())

}