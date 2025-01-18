package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Linkage;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrists;

public class ReleasingActions {
    Wrists wrist;
    LinearSlides linearSlides;
    Linkage linkage;
    Claw claw;
    ClawRotater clawRotater;
    ColorSensor colorSensor;

    AttachmentPositions attachmentPositons;
    double maxExtension = 40.0;
    double slideExtension = maxExtension;
    double clawRotatorAngle = 0.0;

    public void update() {
        wrist.update();
        linkage.update(attachmentPositons.linkageAngle);
        linearSlides.update();
        claw.update();
        clawRotater.update(clawRotatorAngle);
    }

    public ReleasingActions(HardwareMap hardwareMap) {
        wrist = new Wrists(hardwareMap);
        linearSlides = new LinearSlides(hardwareMap);
        linkage = new Linkage(hardwareMap);
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    public Action HP = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Deposit;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            return false;
        }
    };

    public Action PrepareForSpecimin = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Deposit;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.BAR;
            return false;
        }
    };

    public Action HangSpecimen = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Deposit;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            //set to intake to slightly lower the slides to hang the specimen slowly
            linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
            return false;
        }
    };

    public Action PrepareToHang = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Stationary;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.HANG;
            return false;
        }
    };
    public Action Hang = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Stationary;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            return false;
        }
    };


    public Action Release = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            return false;
        }
    };
}
