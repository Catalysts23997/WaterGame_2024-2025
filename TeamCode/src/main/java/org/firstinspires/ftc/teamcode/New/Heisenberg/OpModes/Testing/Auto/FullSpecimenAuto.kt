package org.firstinspires.ftc.teamcode.New.Opmodes.Auto

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.ParallelAction
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.New.Heisenberg.Actions.ReleasingActions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.RunToExactForever
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.T
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drivetrain
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.Utilities.Poses

@TeleOp(name = "FullSpecimenAuto", group = "Linear OpMode")
class FullSpecimenAuto : LinearOpMode() {


    companion object{
        var rT = Poses(19.0,-72.0,0.0)
    }

    override fun runOpMode() {
        val actions = ReleasingActions(hardwareMap)
        telemetry = FtcDashboard.getInstance().telemetry
        FullSpecimenAuto.rT = Poses(19.0,-72.0,0.0)

        val localizer = Localizer(hardwareMap, FullSpecimenAuto.rT)
        val drive = Drivetrain(hardwareMap)

        T.autoType = false

        waitForStart()
        runBlocking(
            ParallelAction(
                Action {
                    localizer.update()
                    RunToExactForever(FullSpecimenAuto.rT)
                    telemetry.addData("hello", FullSpecimenAuto.rT)
                    telemetry.addData("df", Localizer.pose.heading)
                    telemetry.addData("x", Localizer.pose.x)
                    telemetry.addData("y", Localizer.pose.y)
                    telemetry.update()
                    true
                },
                SequentialAction(
                    ParallelAction(
                        Positions.Specimin1.runToExact,
                        actions.SpecimenHangPrep
                    ),
                    actions.SpecimenHang,
                    ParallelAction(
                        Positions.Sample1.runToExact,
                        actions.ForwardIntake,
                    ),
                    actions.Grab,
                    Positions.Player1.runToExact,
                    actions.HPdrop,
                    ParallelAction(
                        Positions.Sample2.runToExact,
                        actions.ForwardIntake,
                    ),
                    actions.Grab,
                    Positions.Player2.runToExact,
                    actions.HPdrop,
                    ParallelAction(
                        Positions.Sample3.runToExact,
                        actions.ForwardIntake,
                    ),
                    actions.Grab,
                    Positions.Player3.runToExact,
                    actions.HPdrop,
                    ParallelAction(
                        Positions.WallIntake.runToExact,
                        actions.WallGrab,
                    ),
                    Positions.WallIntakeFinal.runToExact,
                    actions.Grab,
                    ParallelAction(
                        Positions.Specimin2.runToExact,
                        actions.SpecimenHangPrep
                    ),
                    actions.SpecimenHang,
                    ParallelAction(
                        Positions.WallIntake.runToExact,
                        actions.WallGrab,
                    ),
                    Positions.WallIntakeFinal.runToExact,
                    actions.Grab,
                    ParallelAction(
                        Positions.Specimin3.runToExact,
                        actions.SpecimenHangPrep
                    ),
                    actions.SpecimenHang,
                    ParallelAction(
                        Positions.WallIntake.runToExact,
                        actions.WallGrab,
                    ),
                    Positions.WallIntakeFinal.runToExact,
                    actions.Grab,
                    ParallelAction(
                        Positions.Specimin4.runToExact,
                        actions.SpecimenHangPrep
                    ),
                    actions.SpecimenHang,
                    ParallelAction(
                        Positions.WallIntake.runToExact,
                        actions.WallGrab,
                    ),
                    Positions.WallIntakeFinal.runToExact,
                    actions.Grab,
                    ParallelAction(
                        Positions.Specimin5.runToExact,
                        actions.SpecimenHangPrep
                    ),
                    actions.SpecimenHang,
                    Positions.End.runToExact,
                )
            )
        )
    }
}
