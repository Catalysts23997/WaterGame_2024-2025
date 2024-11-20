//package org.firstinspires.ftc.teamcode.New.Future.Actions
//
//import com.acmerobotics.dashboard.telemetry.TelemetryPacket
//import com.acmerobotics.roadrunner.Action
//import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.Positions
//import org.firstinspires.ftc.teamcode.New.Old.subsystems.AprilTagData
//import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Drive
//
//class AutoDriveToTag(private val aprilTagData: AprilTagData, private val drive: Drive) : Action {
//    override fun run(p: TelemetryPacket): Boolean {
//        drive.state = Drive.States.Auto
////      todo change if not using camera
//        aprilTagData.state = AprilTagData.State.On
//
////        if(Positions.YLbrick3.runToNearest.run(p)){
////            aprilTagData.state = AprilTagData.State.Off
////            drive.state = Drive.States.Manual
////            return false
////        }
////        else{
////            return true
////        }
//    }
//}
