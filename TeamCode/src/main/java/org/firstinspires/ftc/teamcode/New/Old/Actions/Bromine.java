package org.firstinspires.ftc.teamcode.New.Old.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Old.subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Old.subsystems.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.Old.subsystems.WristJohn;

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
        clawRotater.updateTele(gamepad.left_stick_y);
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
 ElapsedTime timer = new ElapsedTime();
 boolean initilized = false;
    public Action AfterS = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!initilized) {
                timer.reset();
                initilized = true;}
            if(timer.seconds() > .4) {
                wrist.state = WristJohn.State.SamplePrep;
                initilized = false;
                return false;
            }
            return true;
        }
    };
    public Action SampleIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.Submersible;
            if(claw.clawState == Claw.ClawState.CLOSED) return false;
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
            shoulder.state = ShoudlerJohn.State.SpecimenDeposit;
            return !shoulder.targetReached;
        }
    };

    public Action prepareSpecimenWallIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = WristJohn.State.WallIntake;
            clawRotater.state = ClawRotater.State.HalfWay;
            shoulder.state = ShoudlerJohn.State.SpecimenIntake;
            claw.clawState = Claw.ClawState.OPEN;
            return false;
        }
    };

    public Action SpecimenWallIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (colorSensor.checkForRecognition()) {
                if(!initilized) {
                    timer.reset();
                    initilized = true;}

                claw.clawState = Claw.ClawState.CLOSED;
                if(timer.seconds() > .8) {
                    initilized = false;
                    return false;
                }
            }
            return true;
        }
    };

    public Action prepForHPdrop = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.CLOSED;
            wrist.state = WristJohn.State.HpDrop;
            shoulder.state = ShoudlerJohn.State.HPdrop;
            return !shoulder.targetReached;
        }
    };

    public Action Drop = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(claw.clawState == Claw.ClawState.CLOSED)claw.clawState = Claw.ClawState.OPEN;
            else claw.clawState = Claw.ClawState.CLOSED;
            return false;
        }
    };

    public Action upperDpad = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shoulder.state = ShoudlerJohn.State.SpecimenIntakeHigh;
            return false;
        }
    };
    public Action lowDpad = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            shoulder.state = ShoudlerJohn.State.SpecimenIntakeLow;
            return false;
        }
    };


}