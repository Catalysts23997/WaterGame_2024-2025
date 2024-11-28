package org.firstinspires.ftc.teamcode.New.Old_Examples.OpModes.Teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

public class JavaTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dash = FtcDashboard.getInstance();
        List<Action> runningActions = new ArrayList<>();

        while(opModeIsActive()&& !isStarted()){
            TelemetryPacket packet = new TelemetryPacket();




            if(gamepad1.a){
                runningActions.add(Action -> false); // inside add() is the action you are running
            }





            // update running actions
            List<Action> newActions = new ArrayList<>();
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;
            dash.sendTelemetryPacket(packet);


        }


    }
}
