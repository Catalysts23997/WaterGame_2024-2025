package org.firstinspires.ftc.teamcode.New.Old.OpModes.Testing.Opmodes.LimitSwitches;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;


import org.firstinspires.ftc.teamcode.New.Old.subsystems.LimitSwitch;

@Disabled
@TeleOp(name = "Limit Switch Op Mode", group = "Switch")
public class LimitSwitchOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
         LimitSwitch limitSwitchSubsystem = new LimitSwitch(hardwareMap.get(DigitalChannel.class, "Switch"),0);


        while (opModeIsActive()) {
            limitSwitchSubsystem.update();

            switch (limitSwitchSubsystem.state) {
                case RELEASED:
                    telemetry.addData("Limit Switch", "Released");
                    break;
                case PRESSED:
                    telemetry.addData("Limit Switch","Pressed");
                    break;
            }


            telemetry.update();
        }
    }
}


