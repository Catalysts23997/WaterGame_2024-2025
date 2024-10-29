package org.firstinspires.ftc.teamcode.New.Future.Actions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.New.Competition.Actions.Positions;
import org.firstinspires.ftc.teamcode.New.Competition.subsystems.ShoudlerJohn;
import org.jetbrains.annotations.NotNull;

public class Testing implements Action {
    ShoudlerJohn shoulder;

    public Testing(ShoudlerJohn object) {
        this.shoulder = object;
    }

    SequentialAction Sequence = new SequentialAction(Positions.BLwall.runTo, Positions.RRbrick2.runTo);

    @Override
    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
        shoulder.state = ShoudlerJohn.State.BASKET;
        Sequence.run(telemetryPacket);
        return false;
    }
}
