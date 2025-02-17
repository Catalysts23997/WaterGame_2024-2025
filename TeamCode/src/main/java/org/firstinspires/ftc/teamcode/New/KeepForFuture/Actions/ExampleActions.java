package org.firstinspires.ftc.teamcode.New.KeepForFuture.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.LinearSlide;
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Subsystems.RotaryMotor;

public class ExampleActions {
    LinearSlide linearSlides;
    public RotaryMotor linkage;


    public void update() {
        linkage.update();
        linearSlides.update();
    }



    public ExampleActions(HardwareMap hardwareMap) {
        linearSlides = new LinearSlide(hardwareMap);
        linkage = new RotaryMotor(hardwareMap);

    }

    public Action example = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            linkage.setState(RotaryMotor.State.Horizontal);
            linearSlides.setState(LinearSlide.SlidesState.IDLE);
            return false;
        }
    };


}
