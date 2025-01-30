package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Auto

//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
//import org.firstinspires.ftc.teamcode.New.Competition.Actions.Bromine
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExact
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.SetDriveTarget
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@Disabled
class AutoNewWork : LinearOpMode() {
    companion object{
        var robot_targetPosition = Poses(-31.0, -65.0, 0.0)
    }
    override fun runOpMode() {

        val localizer = Localizer(hardwareMap, robot_targetPosition)


        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    true
                },

                SequentialAction(
                    SetDriveTarget(Poses(0.0,0.0,0.0)),
                    //move arm
                    SetDriveTarget(Poses(0.0,0.0,0.0)),
                    SetDriveTarget(Poses(0.0,0.0,0.0)),
                    SetDriveTarget(Poses(0.0,0.0,0.0))
                ),

                Action {
                    RunToExact(robot_targetPosition)
                    true
                }
            )
        )


    }
}