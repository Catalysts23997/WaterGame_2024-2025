package com.example.simulation;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class DrakeandAnish_Yoink {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(180, 180, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(30, -57, PI*.5))
                .strafeToSplineHeading(new Vector2d(34.5,-29),PI*.5)
                .strafeToSplineHeading(new Vector2d(34.5,-4),PI*.5)
                .strafeToSplineHeading(new Vector2d(48.5,14),PI*.5)
                .strafeToSplineHeading(new Vector2d(34.5,-4),PI*-.5)
                .strafeToSplineHeading(new Vector2d(34.5,-29),PI*-.5)
                .strafeToSplineHeading(new Vector2d(47,-56),PI*-.25)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(false)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
