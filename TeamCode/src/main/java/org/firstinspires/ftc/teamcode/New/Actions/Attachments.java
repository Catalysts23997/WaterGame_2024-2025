package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Linkage;

public class Attachments {
    public ClawJohn clawJohn;
    public LinearSlides verticalSlides;
    public Linkage linkage;
    //                         HNG    BSK   CLP   SUB GND STA
    //todo name in class, like done for the other one so that you dont commment it, but delcare it via vars
    final int[] slideTargets = {5000, 4000, 3000, 100, 100, 0};

    public Attachments(HardwareMap hardwareMap){
        verticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new ClawJohn(hardwareMap);
        linkage = new Linkage(hardwareMap);
    }

    public void init(){
        verticalSlides.state = LinearSlides.State.STATIONARY;
        clawJohn.clawState = ClawJohn.ClawState.CLOSED;
        linkage.linkageState = Linkage.LinkageState.VERTICAL;
    }

    public Gamepad gamepad2;


    double intakeDistance = 15;

    //gamepad updater for manual intakes and deposits. //todo if passing through entier gampead object note what each button does with comments
    public void update(Gamepad gamepad2){
        this.gamepad2 = gamepad2;
        clawJohn.update(intakeDistance);

        slideTargets[3] = slideTargets [4] = (int) clawJohn.slideLength;

        verticalSlides.update(slideTargets);
        linkage.update(clawJohn.slideDegree);
    }

    //TODO combine auto and manual deposits/intake into one variable action

    public class ManualDeposit implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            clawJohn.armState = ClawJohn.ArmState.DEPOSITING;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;

            //once the gamepad is pressed and the slides are fully extended, release the game piece and reset
            if(verticalSlides.hasReached() && gamepad2.a){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                verticalSlides.state = LinearSlides.State.STATIONARY;
                clawJohn.armState = ClawJohn.ArmState.STATIONARY;
                return false;
            }

            return true;
        }
    }

    //Action to create an object of the class
    public Action manualDeposit(){
        return new ManualDeposit();
    }



    public class ManualIntakeGround implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.OPEN;
            clawJohn.armState = ClawJohn.ArmState.COLLECTING;
            linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
            verticalSlides.state = LinearSlides.State.GROUND;

            intakeDistance += (-0.1 * gamepad2.left_stick_y);


            if(gamepad2.a){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                verticalSlides.state = LinearSlides.State.STATIONARY;
                linkage.linkageState = Linkage.LinkageState.VERTICAL;

                return false;
            }
            else {
                return true;
            }
        }
    }

    public Action manualIntakeGround(){
        return new ManualIntakeGround();
    }

    public class ManualIntakeSub implements Action{
        boolean Deposited = false;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            intakeDistance += (-0.1 * gamepad2.left_stick_y);
            clawJohn.yawState = ClawJohn.YawState.ACTIVE;

            if(gamepad2.a){
                Deposited = true;
            }
            if (Deposited){
                linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.armState = ClawJohn.ArmState.PREPARED;
                verticalSlides.state = LinearSlides.State.STATIONARY;
                verticalSlides.update(slideTargets);
                if(verticalSlides.hasReached()){
                    linkage.linkageState = Linkage.LinkageState.VERTICAL;
                    clawJohn.armState = ClawJohn.ArmState.STATIONARY;
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
                verticalSlides.state = LinearSlides.State.GROUND;
                if(verticalSlides.hasReached()){
                    clawJohn.armState = ClawJohn.ArmState.COLLECTING;
                }
                else{
                    clawJohn.armState = ClawJohn.ArmState.PREPARED;
                }
                return true;
            }
        }
    }

    public Action manualIntakeSub(){
        return new ManualIntakeSub();
    }

}
