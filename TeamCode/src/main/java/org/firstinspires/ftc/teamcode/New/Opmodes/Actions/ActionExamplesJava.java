package org.firstinspires.ftc.teamcode.New.Opmodes.Actions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.New.Future.Actions.Testing;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;

public class ActionExamplesJava extends LinearOpMode {

    @Override
    public void runOpMode() {
        Testing testing = new Testing(new ShoudlerJohn(hardwareMap));
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            TelemetryPacket packet = new TelemetryPacket();
            testing.run(packet);
            Actions.runBlocking(
                    new SequentialAction(
                            testing,
                            testing
                    )
            );
        }
    }
}


