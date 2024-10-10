package com.example.simulation;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Example {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0.0, 0.0, 0.0))

                .lineToX(10)
                .lineToY(10.0)

                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}


enum leftBasket {
    basket(new Vector2d(0.0, 0.0), Rotation2d.fromDouble(0.0)),
    intake(new Vector2d(0.0, 0.0), Rotation2d.fromDouble(0.0));

    leftBasket(Vector2d vector, Rotation2d rotation2d) {
    }
}


