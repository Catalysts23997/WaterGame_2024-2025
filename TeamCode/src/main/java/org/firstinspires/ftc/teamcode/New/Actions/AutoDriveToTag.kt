package org.firstinspires.ftc.teamcode.New.Actions

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.AprilTagData
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.Drive

class AutoDriveToTag(private val aprilTagData: AprilTagData, private val drive: Drive) : Action {
    override fun run(p: TelemetryPacket): Boolean {
        drive.state = Drive.States.Auto
//      todo change if not using camera
        aprilTagData.state = AprilTagData.State.On

        if(Points.basket.runTo.run(p)){
            aprilTagData.state = AprilTagData.State.Off
            drive.state = Drive.States.Manual
            return false
        }
        else{
            return true
        }
    }
}
