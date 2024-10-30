package org.firstinspires.ftc.teamcode.New.Competition.Actions;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Drive;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.WristJohn;

public class DepositBromine{

    public Claw claw;
    public ClawRotater clawrotate;
    public Drive drive;
    public ShoudlerJohn shoudlerJohn;
    public WristJohn wrist;

    public DepositBromine (HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
        clawrotate = new ClawRotater(hardwareMap);
        drive = new Drive(hardwareMap);
        shoudlerJohn = new ShoudlerJohn(hardwareMap);
        wrist = new WristJohn(hardwareMap);


    }
    public class DepositNatalia implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.CLOSED;
            //todo- review claw rotate and fix which position its in
            clawrotate.state = ClawRotater.State.ZERO;
            shoudlerJohn.state = ShoudlerJohn.State.BASKET;
            //idk why the state isn't public, it's public in shoulderjohn so should be accessible here.
            // I'll fix it in the morning.
            wrist.state = WristJohn.state.BASKET;
            //todo- add has reached function to shoudler john
            //idk how to see if it's reached postion, bc there isn't an encoder set up on ShoulderJohn
            if(shoudlerJohn.hasReached()) {
                claw.clawState = Claw.ClawState.OPEN;

                return false;
            }
            return true;
        }

    }


    public Action NataliaDeposit(){return new DepositNatalia();}

    }


