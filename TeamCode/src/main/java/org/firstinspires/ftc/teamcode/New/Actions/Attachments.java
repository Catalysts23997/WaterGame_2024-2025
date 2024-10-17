package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;

public class Attachments {
    public ClawJohn clawJohn;
    public LinearSlides verticalSlides;
    //                         HNG    BSK   CLP   SUB GND STA
    final int[] slideTargets = {5000, 4000, 3000, 100, 100, 0};

    public Attachments(HardwareMap hardwareMap){
        verticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new ClawJohn(hardwareMap);
    }

    public Gamepad gamepad2;

    public void update(Gamepad gamepad2){
        this.gamepad2 = gamepad2;
    }


    public class AutoDeposit implements Action{
        double waitTime;
        ElapsedTime elapsedTime = new ElapsedTime();

        public AutoDeposit (double waitTime){
            this.waitTime = waitTime;
            elapsedTime.reset();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            clawJohn.linkageState = ClawJohn.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;
            clawJohn.update();
            verticalSlides.update(slideTargets);

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

    public Action autoDeposit(double waitTime){
        return new AutoDeposit(waitTime);
    }


    public class ManualDeposit implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            clawJohn.linkageState = ClawJohn.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;
            clawJohn.update();
            verticalSlides.update(slideTargets);

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
            clawJohn.linkageState = ClawJohn.LinkageState.HORIZONTAL;
            verticalSlides.state = LinearSlides.State.GROUND;

            clawJohn.update();
            verticalSlides.update(slideTargets);


            if(elapsedTime.seconds() > waitTime){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                clawJohn.linkageState = ClawJohn.LinkageState.VERTICAL;
                clawJohn.update();
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
            clawJohn.linkageState = ClawJohn.LinkageState.HORIZONTAL;
            verticalSlides.state = LinearSlides.State.GROUND;

            slideTargets[4] += (int) (-10 * gamepad2.left_stick_y);

            clawJohn.update();
            verticalSlides.update(slideTargets);


            if(gamepad2.a){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update();
                verticalSlides.state = LinearSlides.State.STATIONARY;
                clawJohn.linkageState = ClawJohn.LinkageState.VERTICAL;
                clawJohn.update();
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
