package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Linkage;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.VerticalWrist;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.VerticalSlides;


public class Deposit{
    public ClawJohn clawJohn;
    public LinearSlides VerticalSlides;
    public Linkage linkage;

    public Deposit (HardwareMap hardwareMap){
        VerticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new ClawJohn(hardwareMap);
        linkage = new Linkage(hardwareMap);
    }
    public void init(){
        VerticalSlides.state = LinearSlides.State.STATIONARY;
        clawJohn.clawState = ClawJohn.ClawState.CLOSED;
        linkage.linkageState = Linkage.LinkageState.VERTICAL;
    }
    // public Gamepad gamepad1;

    public class AutoDeposit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VerticalSlides.state = LinearSlides.State.BASKET;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            if(VerticalSlides.hasReached()){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                return false;
            }

            return true;
        }

    }
    public Action AutoDeposit(){return new AutoDeposit();}
}

