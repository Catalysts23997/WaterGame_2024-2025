package org.firstinspires.ftc.teamcode.New.Actions;

import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

public enum Points {
    basket(new Vector2d(0.0, 0.0), com.acmerobotics.roadrunner.Rotation2d.fromDouble(0.0)),
    intake(new Vector2d(0.0, 0.0), Rotation2d.fromDouble(0.0));

    Points(Vector2d vector, Rotation2d rotation2d) {
        runTo = new RunTo(vector, rotation2d);
    }

    public final RunTo runTo;
}
