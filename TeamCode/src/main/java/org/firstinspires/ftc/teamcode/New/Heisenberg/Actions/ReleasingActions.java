package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import static java.lang.Math.PI;

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
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;

public class ReleasingActions {
    Wrists wrist;
    LinearSlides linearSlides;
    Linkage linkage;
    Claw claw;
    ClawRotater clawRotater;
    double clawRotatorAngle = 0.0;

    public void update(double cR) {
        wrist.update();
//        linkage.update();
//        linearSlides.update();
        claw.update();
        clawRotatorAngle = Math.PI * cR;
        clawRotater.update(clawRotatorAngle);
    }
    public void update() {
        wrist.update();
//        linkage.update();
//        linearSlides.update();
        claw.update();
        clawRotatorAngle = Localizer.pose.getHeading();
        clawRotater.update(clawRotatorAngle);
    }


    public ReleasingActions(HardwareMap hardwareMap) {
        wrist = new Wrists(hardwareMap);
        linearSlides = new LinearSlides(hardwareMap);
        linkage = new Linkage(hardwareMap);
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
    }

    public Action HPdrop = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.DropSample;
//            linkage.setState(Linkage.State.Horizontal);
            clawRotatorAngle = 0.0;
//            linearSlides.setState(  LinearSlides.SlidesState.WALL);
           claw.clawState = Claw.ClawState.OPEN;
           return false;
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
//            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
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
//            linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
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
//            linearSlides.slidesState = LinearSlides.SlidesState.BAR;
            if (elapsedTime.seconds() > 0.3){
                //intake position is used to lower the slides slightly
//                linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            }
            else {
                return true;
            }
        }
    };

    public Action Basket = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.Basket;
            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.BAR;
            if (elapsedTime.seconds() > 0.3){
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
//            linearSlides.slidesState = LinearSlides.SlidesState.HANG;
            //                linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            return !(elapsedTime.seconds() > 0.3);
        }
    };

    public Action pickUp = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeGround;
            if (elapsedTime.seconds() > 0.3){
                claw.clawState = Claw.ClawState.CLOSED;
                return true;
            } else if (elapsedTime.seconds()>.6) {
                wrist.state = Wrists.State.DropSample;
                return false;
            } else {
                return true;
            }
        }
    };
}
