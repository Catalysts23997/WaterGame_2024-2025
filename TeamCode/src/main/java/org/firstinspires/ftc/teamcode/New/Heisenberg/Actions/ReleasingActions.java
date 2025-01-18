package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Linkage;
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
        linkage.update();
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
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.DropSample;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            if (elapsedTime.seconds() > 0.3){
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            }
            else {
                return true;
            }
        }
    };

    public Action WallGrab = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeWall;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            if (elapsedTime.seconds() > 0.3){
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            }
            else {
                return true;
            }
        }
    };
    public Action GroundIntake = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeGround;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
            //change elapsed time to camera output
            if (elapsedTime.seconds() > 0.3){
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            }
            else {
                return true;
            }
        }
    };



    public Action SpecimenHang = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Basket;
            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.BAR;
            if (elapsedTime.seconds() > 0.3){
                //intake position is used to lower the slides slightly
                linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            }
            else {
                return true;
            }
        }
    };

    public Action Hang = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.DropSample;
            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
            linearSlides.slidesState = LinearSlides.SlidesState.HANG;
            if (elapsedTime.seconds() > 0.3){
                linearSlides.slidesState = LinearSlides.SlidesState.WALL;
                return false;
            }
            else {
                return true;
            }
        }
    };
}
