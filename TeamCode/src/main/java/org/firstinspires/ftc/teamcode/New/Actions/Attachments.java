package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;

public class Attachments {
    public ClawJohn clawJohn;
    public LinearSlides verticalSlides;
    //                         HNG    BSK   CLP   SUB GND STA
    final int[] slideTargets = {5000, 4000, 3000, 100, 0, 0};
    int extenderGoal = 50;
    final int stationaryGoal = 0;
    final int maxExtension = 100;
    final int maxRetraction = 20;

    public Attachments(HardwareMap hardwareMap){
        verticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new ClawJohn(hardwareMap);
        clawJohn.stationaryGoal = stationaryGoal;
        clawJohn.maxExtension = maxExtension;
        clawJohn.maxRetaction = maxRetraction;
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
            clawJohn.flipperState = ClawJohn.FlipperState.DEPOSIT;
            clawJohn.extenderState = ClawJohn.ExtenderState.RETRACTED;
            verticalSlides.state = LinearSlides.State.BASKET;
            clawJohn.update(extenderGoal);
            verticalSlides.update(slideTargets);

            if(verticalSlides.hasReached() && elapsedTime.seconds() > waitTime){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                clawJohn.update(extenderGoal);
                return false;
            }

            return true;
        }
    }

    public Action autoDeposit(double waitTime){
        return new AutoDeposit(waitTime);
    }


    public class ManualDeposit implements Action{
        boolean condition;
        ElapsedTime elapsedTime = new ElapsedTime();

        public ManualDeposit(boolean condition){
            this.condition = condition;
            elapsedTime.reset();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            clawJohn.flipperState = ClawJohn.FlipperState.DEPOSIT;
            clawJohn.extenderState = ClawJohn.ExtenderState.RETRACTED;
            verticalSlides.state = LinearSlides.State.BASKET;
            clawJohn.update(extenderGoal);
            verticalSlides.update(slideTargets);

            if(verticalSlides.hasReached() && condition){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                clawJohn.update(extenderGoal);
                return false;
            }

            return true;
        }
    }

    public Action manualDeposit(boolean condition){
        return new ManualDeposit(condition);
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
            clawJohn.flipperState = ClawJohn.FlipperState.COLLECTION;
            clawJohn.extenderState = ClawJohn.ExtenderState.EXTENDING;
            verticalSlides.state = LinearSlides.State.GROUND;

            extenderGoal = 70;

            clawJohn.update(extenderGoal);
            verticalSlides.update(slideTargets);


            if(elapsedTime.seconds() > waitTime){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update(extenderGoal);
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
        ElapsedTime elapsedTime = new ElapsedTime();

        boolean condition;
        double Gamepad2leftstickY;

        public ManualIntake (boolean condition, double Gamepad2leftstickY){
            this.condition = condition;
            this.Gamepad2leftstickY = Gamepad2leftstickY;
            elapsedTime.reset();
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            clawJohn.clawState = ClawJohn.ClawState.OPEN;
            clawJohn.flipperState = ClawJohn.FlipperState.COLLECTION;
            clawJohn.extenderState = ClawJohn.ExtenderState.EXTENDING;
            verticalSlides.state = LinearSlides.State.GROUND;

            extenderGoal += (int) (-10 * Gamepad2leftstickY);

            clawJohn.update(extenderGoal);
            verticalSlides.update(slideTargets);


            if(condition){
                clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                clawJohn.update(extenderGoal);
                return false;
            }
            else {
                return true;
            }
        }
    }

    public Action manualIntake(boolean condition, double Gamepad2leftstickY){
        return new ManualIntake(condition, Gamepad2leftstickY);
    }

}
