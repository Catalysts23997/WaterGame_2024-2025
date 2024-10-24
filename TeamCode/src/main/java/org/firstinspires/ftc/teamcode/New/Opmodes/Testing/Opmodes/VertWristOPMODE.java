package org.firstinspires.ftc.teamcode.New.Opmodes.Testing.Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.Claws;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.VerticalWrist;

public class VertWristOPMODE extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        VerticalWrist verticalwrist = new VerticalWrist(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad1.y){
              verticalwrist.position = VerticalWrist.Position.Top;
            }
            if(gamepad1.a){
                verticalwrist.position = VerticalWrist.Position.Bottom;
            }
            verticalwrist.update();
        }
    }
}
