package org.firstinspires.ftc.teamcode.New.Actions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ShoudlerJohn;
import org.jetbrains.annotations.NotNull;

public class Testing implements Action {
    ShoudlerJohn shoulder;

    public Testing(ShoudlerJohn object) {
        this.shoulder = object;
    }

    SequentialAction Sequence = new SequentialAction(Points.basket.runTo, Points.intake.runTo);

    @Override
    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
        shoulder.state = ShoudlerJohn.State.BASKET;
        Sequence.run(telemetryPacket);
        return false;
    }
}
