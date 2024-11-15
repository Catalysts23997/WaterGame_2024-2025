package org.firstinspires.ftc.teamcode.New.Future;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Future.SubSystems.AttachmentsJohn;
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Future.SubSystems.Linkage;


public class Deposit{
    public AttachmentsJohn clawJohn;
    public LinearSlides VerticalSlides;
    public Linkage linkage;


    public Deposit (HardwareMap hardwareMap){
        VerticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new AttachmentsJohn(hardwareMap);
        linkage = new Linkage(hardwareMap);

        VerticalSlides.state = LinearSlides.State.STATIONARY;
        clawJohn.clawState = AttachmentsJohn.ClawState.CLOSED;
        linkage.linkageState = Linkage.LinkageState.VERTICAL;
    }
    // public Gamepad gamepad1;

    public class AutoDeposit implements Action{
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VerticalSlides.state = LinearSlides.State.BASKET;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            clawJohn.clawState = AttachmentsJohn.ClawState.CLOSED;
            if(VerticalSlides.hasReached()){
                clawJohn.clawState = AttachmentsJohn.ClawState.OPEN;
                return false;
            }
            return true;
        }

    }
    public Action AutoDeposit(){return new AutoDeposit();}
}

