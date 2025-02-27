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
        clawRotatorAngle = cR;
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

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            wrist.state = Wrists.State.In;
            linkage.setState(Linkage.State.Horizontal);
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.WALL);
           claw.clawState = Claw.ClawState.OPEN;
            return false;
        }
    };

    public Action WallGrab = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeWall;
            linkage.setState(Linkage.State.Vertical);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.WALL);
            return false;

        }
    };
    public Action ForwardIntake = new Action() {
        ElapsedTime t = new ElapsedTime(); // Ensure initialization at declaration
        boolean start = true;

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (start) {
                t.reset(); // Properly reset time every execution
                start = false;
            }

            wrist.state = Wrists.State.In;
            linkage.setState(Linkage.State.Horizontal);
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.INTAKE);

            if (t.seconds() > 0.4) {
                claw.clawState = Claw.ClawState.OPEN;
                wrist.state = Wrists.State.DropSample;
//                start = true;
                return false;
            }

            return true; // Continue running
        }
    };

    public Action Grab = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.CLOSED;
            return false;
        }
    };public Action GrabPre = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.IntakeFront;
            return false;
        }
    };

    public Action Release = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            wrist.state = Wrists.State.In;
            return false;
        }
    };

    public Action BackIntake = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            linkage.setState(Linkage.State.Vertical);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.IDLE);
            return false;
        }
    };



    public Action SpecimenHangPrep = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            wrist.state = Wrists.State.SpecimenHang;
            linkage.setState(Linkage.State.Specimin);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.Specimen);
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
            linkage.setState(Linkage.State.Specimin);
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.13;
            linearSlides.setState(LinearSlides.SlidesState.SpecimenHang);

            if (elapsedTime.seconds() > 0.3) {
                claw.clawState = Claw.ClawState.OPEN;
                return false;
            } else {
                return true;
            }
        }
    };
    public Action Retract = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            linearSlides.setState(LinearSlides.SlidesState.IDLE);
            wrist.state = Wrists.State.In;

            return false;
        }
    };

    public Action Basket = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        boolean start = true;
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (start){
                elapsedTime.reset();
                start=false;
            }
            wrist.state = Wrists.State.Basket;
            linkage.setState(Linkage.State.Basket);
            clawRotatorAngle = 0.13;
            if(elapsedTime.seconds()>1) {
                linearSlides.setState(LinearSlides.SlidesState.BASKET);
                return false;
            }
            else{
                return true;
            }


        }
    };
    public Action Grabs = new Action() {

        final ElapsedTime openTime = new ElapsedTime();

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(openTime.seconds() <= .5){
                claw.clawState = Claw.ClawState.CLOSED;
                return true;
            }
            else{
                linearSlides.setState(LinearSlides.SlidesState.IDLE);
                return false;
            }
        }
    };


    public Action Hang = new Action() {
        final ElapsedTime elapsedTime = new ElapsedTime();
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//            linkage.setState(Linkage.State.Basket);
            claw.clawState = Claw.ClawState.OPEN;
            clawRotatorAngle = 0.13;
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
