package org.firstinspires.ftc.teamcode.New.Competition.Actions;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Competition.subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;

public class Bromine {
    public Claw claw;
    public ClawRotater clawRotater;
    public ShoudlerJohn shoulder;
    public ColorSensor colorSensor;

    public Bromine(HardwareMap hardwareMap) {
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        shoulder = new ShoudlerJohn(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    double clawRotaterAngle;
    //TODO have a way to calculate the yaw of the claw with camera

    public void update(Gamepad gamepad) {
        claw.update();
        clawRotater.update(clawRotaterAngle);
        shoulder.update();
    }

    public void update() {
        claw.update();
        clawRotater.update(clawRotaterAngle);
        shoulder.update();
    }

    class Intake implements Action {
        boolean grabbingFromGround;

        public Intake(boolean grabbingFromGround) {
            this.grabbingFromGround = grabbingFromGround;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

            claw.clawState = Claw.ClawState.OPEN;
            if (grabbingFromGround) {
                clawRotater.state = ClawRotater.State.ADJUSTING;
                shoulder.state = ShoudlerJohn.State.GROUND;
            } else {
                clawRotater.state = ClawRotater.State.INPUT;
                shoulder.state = ShoudlerJohn.State.SUBMERSIBLE;
            }

            if (colorSensor.checkForRecognition() == ColorSensor.Recognition.IN) {
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            } else {
                return true;
            }
        }
    }

    ParallelAction driveAndRaise = new ParallelAction(
            Positions.Rbasket.runToNearest,
            telemetryPacket2 -> {

                //This is a new action - place actual deposit work here

                return true;
            }
    );

    public SequentialAction depositBasket = new SequentialAction(
            driveAndRaise,
            telemetryPacket2 -> {


                //Final Deposit


                return true;
            }
    );

    public Action intake(boolean grabbingFromGround) {
        return new Intake(grabbingFromGround);
    }
}