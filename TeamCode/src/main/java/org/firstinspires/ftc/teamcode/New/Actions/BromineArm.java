package org.firstinspires.ftc.teamcode.New.Actions;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.Claw;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ClawRotater;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ShoudlerJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Kotlin.ColorSensor;

import bsh.org.objectweb.asm.ClassWriter;

public class BromineArm {
    public Claw claw;
    public ClawRotater clawRotater;
    public ShoudlerJohn shoulder;
    public ColorSensor colorSensor;

    public BromineArm (HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        shoulder = new ShoudlerJohn(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    double clawRotaterAngle;
    //TODO have a way to calculate this with camerag

    public void update(Gamepad gamepad){
        claw.update();
        clawRotater.update(clawRotaterAngle);
        shoulder.update();
    }

    public class Intake implements Action {

        boolean grabbingFromGround;

        public Intake (boolean grabbingFromGround){
            this.grabbingFromGround = grabbingFromGround;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.clawState = Claw.ClawState.OPEN;
            if(grabbingFromGround){
                clawRotater.state = ClawRotater.State.ADJUSTING;
                shoulder.state = ShoudlerJohn.State.GROUND;
            }
            else {
                clawRotater.state = ClawRotater.State.INPUT;
                shoulder.state = ShoudlerJohn.State.SUBMERSIBLE;
            }

            if (colorSensor.checkForRecognition() == ColorSensor.Recognition.IN){
                claw.clawState = Claw.ClawState.CLOSED;
                return false;
            }
            else {
                return true;
            }
        }
    }

    public Action intake(boolean grabbingFromGround) {
        return new Intake(grabbingFromGround);
    }
}