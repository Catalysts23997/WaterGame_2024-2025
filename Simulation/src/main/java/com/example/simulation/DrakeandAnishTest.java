package com.example.simulation;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import org.jetbrains.annotations.NotNull;

public class DrakeandAnishTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-30, -57, PI*.5))
                .strafeToSplineHeading(new Vector2d(-53,-53),-PI*.75)
                .strafeToSplineHeading(new Vector2d(-48,-38.),PI*.5)
                .strafeToSplineHeading(new Vector2d(-53,-53),-PI*.75)
                .strafeToSplineHeading(new Vector2d(-31.7,-10),-PI*.0)
                .strafeToSplineHeading(new Vector2d(-25,-10),-PI*.0)
                .strafeToSplineHeading(new Vector2d(-58.75,-10),PI*1.5)
                .strafeToSplineHeading(new Vector2d(-53,-53),-PI*.75)

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

enum b {
    basket(new Vector2d(0.0, 0.0), Rotation2d.fromDouble(0.0)),
    intake(new Vector2d(0.0, 0.0), Rotation2d.fromDouble(0.0));

    b(Vector2d vector, Rotation2d rotation2d) {
    }
}



