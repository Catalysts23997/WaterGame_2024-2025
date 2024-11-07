package org.firstinspires.ftc.teamcode.New.Competition.Actions;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.WristJohn;

public class Bromine {
    public Claw claw;
    public ClawRotater clawRotater;
    public ShoudlerJohn shoulder;
    public WristJohn wrist;

    public Bromine(HardwareMap hardwareMap) {
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        shoulder = new ShoudlerJohn(hardwareMap);
        wrist = new WristJohn(hardwareMap);
    }

    //TODO have a way to calculate the yaw of the claw with camera

    public void teleUpdate(Gamepad gamepad, double looptime) {
        claw.update();
        clawRotater.updateTele(gamepad.left_stick_y);
        shoulder.update(looptime);
        wrist.update();
    }

    public void teleUpdate(double looptime) {
        claw.update();
        clawRotater.update();
        shoulder.update(looptime);
    }


    class Intake implements Action {
        boolean grabFromFloor;

        public Intake(boolean grabFromFloor) {
            this.grabFromFloor = grabFromFloor;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            if (grabFromFloor) {
                clawRotater.state = ClawRotater.State.ADJUSTING;
                shoulder.state = ShoudlerJohn.State.IDLE_TO_Ground;
            } else {
//                clawRotater.angle = ;
                clawRotater.state = ClawRotater.State.INPUT;
                shoulder.state = ShoudlerJohn.State.IDLE_TO_Submersible;
            }

            //color sensor not added yet
            if (1==1) {
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            } else {
                return true;
            }
        }
    }
    //runs to nearest is completed, simultaneously the shoudler is raised and claw is kept closed.

    ParallelAction driveAndRaise = new ParallelAction(
            Positions.Rbasket.runToNearest,
            telemetryPacket2 -> {
                shoulder.state = ShoudlerJohn.State.IDLE_TO_Basket;
                return !shoulder.targetReached;
            }
    );
    //after the drive and raise action is completed, the claw opens.

    boolean initilized = false;
    ElapsedTime timer = new ElapsedTime();
    double time = 0.8;
    SequentialAction DepositBasket = new SequentialAction(
            driveAndRaise,
            telemetryPacket2 -> {
                claw.clawState = Claw.ClawState.OPEN;
                if (!initilized) {
                    timer.reset();
                    initilized = true;
                }
                if(timer.seconds()>time){
                    initilized = false;
                    return false;
                } else return true;
            }
    );

    public Action intake(boolean grabbingFromGround) {
        return new Intake(grabbingFromGround);
    }

    public Action depositBasket(double time) {
        this.time = time;
        return DepositBasket;
    }

    public class DepositSpecimen implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shoulder.state = ShoudlerJohn.State.IDLE_TO_Submersible;
            claw.clawState = Claw.ClawState.CLOSED;
            if (shoulder.targetReached){
                claw.clawState = Claw.ClawState.OPEN;

            }
            return false;
        }
    }
}