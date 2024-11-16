package org.firstinspires.ftc.teamcode.New.Competition.Actions;

import android.util.Log;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    public ColorSensor colorSensor;
    public boolean isAuto = false;

    public Bromine(HardwareMap hardwareMap) {
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        shoulder = new ShoudlerJohn(hardwareMap);
        wrist = new WristJohn(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    //TODO have a way to calculate the yaw of the claw with camera

    public void teleUpdate(Gamepad gamepad) {
        claw.update();
        clawRotater.updateTele(gamepad.right_stick_x);
        shoulder.update();
        wrist.update();
    }
    public void updateAuto() {
        claw.update();
        clawRotater.update();
        shoulder.update();
        wrist.update();
    }

    public Action prepareSampleIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            if (isAuto) {
                clawRotater.state = ClawRotater.State.ADJUSTING;
            } else {
                clawRotater.state = ClawRotater.State.INPUT;
            }
            wrist.state = WristJohn.State.SamplePrep;
            shoulder.state = ShoudlerJohn.State.SubmersibleIntake;
            return false;
        }
    };

    public Action SampleIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.Submersible;

            if (colorSensor.checkForRecognition()) {
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            } else {
                return true;
            }
        }
    };

    //runs to nearest is completed, simultaneously the shoulder is raised and claw is kept closed.

    public Action prepareBasketDeposit = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shoulder.state = ShoudlerJohn.State.BasketDeposit;
            wrist.state = WristJohn.State.Basket;
            clawRotater.state = ClawRotater.State.HalfWay;
            return !shoulder.targetReached;
        }
    };

    public Action prepareSpecimenDeposit = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.Upwards;
            clawRotater.state = ClawRotater.State.HalfWay;
            claw.clawState = Claw.ClawState.CLOSED;
            shoulder.state = ShoudlerJohn.State.SpecimenDepositPrep;
            return !shoulder.targetReached;
        }
    };

    public Action fullSpecimenDeposit = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.Upwards;
            clawRotater.state = ClawRotater.State.HalfWay;
            claw.clawState = Claw.ClawState.CLOSED;
            shoulder.state = ShoudlerJohn.State.SpecimenDeposit;
            if (shoulder.targetReached) {
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            } else {
                return true;
            }
        }
    };

    public Action prepareSpecimenWallIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.WallIntake;
            clawRotater.state = ClawRotater.State.HalfWay;
            shoulder.state = ShoudlerJohn.State.SpecimenIntake;
            return !shoulder.targetReached;
        }
    };

    public Action SpecimenWallIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (colorSensor.checkForRecognition()) {
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            }
            return true;
        }
    };

    public Action prepForHPdrop = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.HpDrop;
            shoulder.state = ShoudlerJohn.State.HPdrop;
            return false;
        }
    };

    public Action Drop = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            return false;
        }
    };


}