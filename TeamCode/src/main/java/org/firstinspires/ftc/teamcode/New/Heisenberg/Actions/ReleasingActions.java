package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Linkage;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrists;
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;

public class ReleasingActions {
    Wrists wrist;
    LinearSlides linearSlides;
    public Linkage linkage;
    Claw claw;
    ClawRotater clawRotater;
    double clawRotatorAngle = 0.0;

    public void update(double cR) {
        wrist.update();
        linkage.update();
        linearSlides.update();
        claw.update();
        clawRotatorAngle = Math.PI * cR;
        clawRotater.update(clawRotatorAngle);
    }

    public void update() {
        wrist.update();
        linkage.update();
        linearSlides.update();
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
        final ElapsedTime elapsedTime = new ElapsedTime();
        boolean start = true;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (start){
                elapsedTime.reset();
                start=false;
            }
            wrist.state = Wrists.State.DropSample;
            linkage.setState(Linkage.State.Horizontal);
            clawRotatorAngle = 0.0;
//            linearSlides.setState(  LinearSlides.SlidesState.WALL);
           claw.clawState = Claw.ClawState.CLOSED;
            if (elapsedTime.seconds() > 1.0){
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            }
            else {
                return true;
            }
        }
    };

    public Action WallGrab = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeWall;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
                return false;

        }
    };
    public Action ForwardIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeFront;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
            return false;
        }
    };

    public Action Grab = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.CLOSED;
            return false;
        }
    };

    public Action BackIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeBack;
            linkage.setState(Linkage.State.Horizontal);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.INTAKE;
            return false;
        }
    };



    public Action SpecimenHangPrep = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.SpecimenHang;
            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.BAR;
            return false;
        }
    };
    public Action SpecimenHang = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        boolean start = true;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (start){
                elapsedTime.reset();
                start=false;
            }
            wrist.state = Wrists.State.SpecimenHang;
            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.WALL;
            if (elapsedTime.seconds() > 0.3) {
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            } else {
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
            clawRotatorAngle = 0.0;
//            linearSlides.slidesState = LinearSlides.SlidesState.BASKET;
            return false;
        }
    };
    public Action Toggle = new Action() {

        final ElapsedTime openTime = new ElapsedTime();
        final ElapsedTime closedTime = new ElapsedTime();

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (claw.clawState == Claw.ClawState.OPEN && openTime.seconds() > 0.5) {
                claw.clawState = Claw.ClawState.CLOSED;
                closedTime.reset();
            } else if (closedTime.seconds() > 0.5) {
                claw.clawState = Claw.ClawState.OPEN;
                openTime.reset();
            }
            return false;
        }
    };



    public Action Hang = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeBack;
//            linkage.setState(Linkage.State.Basket);
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
//            linearSlides.setState(LinearSlides.SlidesState.BAR);
            linkage.setState(Linkage.State.Horizontal);
            wrist.state = Wrists.State.IntakeFront;

            if (elapsedTime.seconds() > 0.4){
                claw.clawState = Claw.ClawState.CLOSED;
                return true;
            } else if (elapsedTime.seconds()>.7) {
                wrist.state = Wrists.State.DropSample;
                return false;
            } else {
                return true;
            }
        }
    };

    public Action bringDOwnLinkage = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            linkage.getMotor().setPower(-.6);
            if (elapsedTime.seconds() > 0.25){
                linkage.getMotor().setPower(0.0);
                return false;
            }
            else {
                return true;
            }
        }
    };

}
