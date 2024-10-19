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

    //gamepad updater for manual intakes and deposits.
    public void update(Gamepad gamepad2){
        this.gamepad2 = gamepad2;
        clawJohn.update();
        verticalSlides.update(slideTargets);
        linkage.update();
    }

    //TODO combine auto and manual deposits/intake into one variable action
    public class AutoDeposit implements Action{
        double waitTime;
        ElapsedTime elapsedTime = new ElapsedTime();

        public AutoDeposit (double waitTime){
            //Uses wait time from object of Action
            this.waitTime = waitTime;
            elapsedTime.reset();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //Set the claw and slide positions to be ready to drop
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;

            clawJohn.update();
            verticalSlides.update(slideTargets);
            linkage.update();

            //once the time is up and the slides are fully extended, release the game piece and reset
            if(verticalSlides.hasReached() && elapsedTime.seconds() > waitTime){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                verticalSlides.update(slideTargets);
                return false;
            }

            return true;
        }
    }

    //Action to create an object of the class
    public Action autoDeposit(double waitTime){
        return new AutoDeposit(waitTime);
    }


    public class ManualDeposit implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;
            clawJohn.update();
            verticalSlides.update(slideTargets);
            linkage.update();

            //once the gamepad is pressed and the slides are fully extended, release the game piece and reset
            if(verticalSlides.hasReached() && gamepad2.a){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                verticalSlides.update(slideTargets);
                return false;
            }

            return true;
        }
    }

    //Action to create an object of the class
    public Action manualDeposit(){
        return new ManualDeposit();
    }


    public class AutoIntake implements Action{
        ElapsedTime elapsedTime = new ElapsedTime();

        double waitTime;

        public AutoIntake (double waitTime){
            this.waitTime = waitTime;
            elapsedTime.reset();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.OPEN;
            linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
            verticalSlides.state = LinearSlides.State.GROUND;

            clawJohn.update();
            verticalSlides.update(slideTargets);
            linkage.update();


            if(elapsedTime.seconds() > waitTime){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                linkage.linkageState = Linkage.LinkageState.VERTICAL;
                linkage.update();
                verticalSlides.update(slideTargets);
                return false;
            }
            else {
                return true;
            }
        }
    }

    public Action autoIntake(double waitTime){
        return new AutoIntake(waitTime);
    }


    public class ManualIntake implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.OPEN;
            linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
            verticalSlides.state = LinearSlides.State.GROUND;

            slideTargets[4] += (int) (-10 * gamepad2.left_stick_y);

            clawJohn.update();
            verticalSlides.update(slideTargets);
            linkage.update();


            if(gamepad2.a){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                linkage.linkageState = Linkage.LinkageState.VERTICAL;
                linkage.update();
                verticalSlides.update(slideTargets);
                return false;
            }
            else {
                return true;
            }
        }
    }

    public Action manualIntake(){
        return new ManualIntake();
    }

}
