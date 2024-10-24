package org.firstinspires.ftc.teamcode.New.Actions;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.Claw;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ClawRotater;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ShoudlerJohn;

import bsh.org.objectweb.asm.ClassWriter;

public class BromineArm {
    public Claw claw;
    public ClawRotater clawRotater;
    public ShoudlerJohn shoulder;

    public BromineArm (HardwareMap hardwareMap){
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        shoulder = new ShoudlerJohn(hardwareMap);
    }

    public class Intake implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            return false;
        }
    }

    public Action intake() {
        return new Intake();
    }
}