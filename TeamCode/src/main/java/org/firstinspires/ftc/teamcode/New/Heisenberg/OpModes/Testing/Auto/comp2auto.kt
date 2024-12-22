package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Auto
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
class comp2auto: LinearOpMode() {
    override fun runOpMode() {
        val localizer = Localizer(hardwareMap, Localizer.Poses(0.0, 0.0, 0.0))
        waitForStart()
        SequentialAction(
            Positions.CornerOfRedZone.runToExact,

        )


    }
}