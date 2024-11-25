package org.firstinspires.ftc.teamcode.New.Old_Examples.Actions;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.New.Old_Examples.subsystems.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrist;




// i made this for a deposit action, but it appears to be no longer needed
//since bromine has actions within parallel and sequential actions, the actual "depositing" part of the code was moved there.
public class BromineDeposit{

    public Claw claw;
    public ClawRotater clawrotate;
    public Drive drive;
    public ShoudlerJohn shoudlerJohn;
    public Wrist wrist;

    public BromineDeposit (HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
        clawrotate = new ClawRotater(hardwareMap);
        drive = new Drive(hardwareMap);
        shoudlerJohn = new ShoudlerJohn(hardwareMap);
        wrist = new Wrist(hardwareMap);


    }
    public class DepositNatalia implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.CLOSED;
            //todo- review claw rotate and fix which position its in
            clawrotate.state = ClawRotater.State.ZERO;
//            shoudlerJohn.state = ShoudlerJohn.State.BASKET;
            //idk why the state isn't public, it's public in shoulderjohn so should be accessible here.
            // I'll fix it in the morning.
//            wrist.state = WristJohn.state.BASKET;
//            //todo- add has reached function to shoudler john
//            //idk how to see if it's reached postion, bc there isn't an encoder set up on ShoulderJohn
//            if(shoudlerJohn.hasReached()) {
//                claw.clawState = Claw.ClawState.OPEN;

                return false;
            }

        }
    public Action NataliaDeposit(){return new DepositNatalia();}

}




