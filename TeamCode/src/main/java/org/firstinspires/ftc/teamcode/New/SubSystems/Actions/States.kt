package org.firstinspires.ftc.teamcode.New.SubSystems.Actions

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Opmodes.Teleop.JohnTele
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Bucket
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Intake
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Intake.IntakeState
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Intake.FlipperState

class States(hardwareMap: HardwareMap) {

    inner class Deposit : Action{

        override fun run(p: TelemetryPacket): Boolean {

            return false
        }

    }


    inner class Intake : Action{

        override fun run(p: TelemetryPacket): Boolean {

            return false
        }

    }


}