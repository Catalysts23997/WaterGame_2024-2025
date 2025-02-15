package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Auto

//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExact
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExactForever
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.SetDriveTarget
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@TeleOp(name = "AAAA", group = "Linear OpMode")
class AutoNewWork : LinearOpMode() {
    companion object{
        var robot_targetPosition = Poses(0.0, 0.0, 0.0)
    }
    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, robot_targetPosition)
        val drive = Drive(hardwareMap)


        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    RunToExactForever(robot_targetPosition)
                    true
                },

                SequentialAction(
                    SetDriveTarget(Poses(5.0,20.0,0.0)),
                    SleepAction(1.0),
                    //move arm
                    SetDriveTarget(Poses(-35.0,10.0,-Math.PI*.7)),
                    SetDriveTarget(Poses(5.0,20.0,0.0)),

                )
            )
        )


    }
}