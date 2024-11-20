package org.firstinspires.ftc.teamcode.New.Old.Actions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.New.Old.subsystems.ShoudlerJohn;
import org.jetbrains.annotations.NotNull;

public class Testing implements Action {
    ShoudlerJohn shoulder;

    public Testing(ShoudlerJohn object) {
        this.shoulder = object;
    }

//    SequentialAction Sequence = new SequentialAction(Positions.BLwall.runToNearest, Positions.RRbrick2.runToNearest);

    @Override
    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
//        shoulder.state = ShoudlerJohn.State.BASKET;
//        Sequence.run(telemetryPacket);
        return false;
    }
}
