package org.firstinspires.ftc.teamcode.New.Old_Examples.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions;
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;

public class AutoInJava extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Localizer localizer = new Localizer(hardwareMap, new Localizer.Poses(0.0,0.0,0.0));


        waitForStart();
        Actions.runBlocking(

                new ParallelAction(
                        telemetryPacket -> {
                            localizer.update();
                            return true;
                        },
                        new SequentialAction(
                                Positions.BackLeftSidePanel.runToExact
                        )
                )
        );

    }
}
