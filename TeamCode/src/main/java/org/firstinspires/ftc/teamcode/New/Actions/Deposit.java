package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;

public class Deposit implements Action {
    ClawJohn clawJohn;
    LinearSlides verticalSlides;
    int[] targets;

    public Deposit(ClawJohn clawObject, LinearSlides slidesObject, int[] slideTargets){
        this.clawJohn = clawObject;
        this.verticalSlides = slidesObject;
        this.targets = slideTargets;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        clawJohn.clawState = ClawJohn.ClawState.CLOSED;
        clawJohn.flipperState = ClawJohn.FlipperState.DEPOSIT;
        clawJohn.extenderState = ClawJohn.ExtenderState.RETRACTED;
        verticalSlides.state = LinearSlides.State.BASKET;
        clawJohn.update(0, 0);
        verticalSlides.update(targets);

        if(verticalSlides.hasReached()){
            clawJohn.clawState = ClawJohn.ClawState.OPEN;
            clawJohn.update(0, 0);
            return false;
        }

        return true;
    }
}
