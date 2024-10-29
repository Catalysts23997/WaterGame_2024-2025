package org.firstinspires.ftc.teamcode.New.Competition.Actions;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

public enum Positions {

        //Bricks Y+B+R = colors L + R = Side of the map
//        BLbrick1 (new Vector2d(-68, 25.5), Rotation2d.fromDouble(0.0)),
//        BLbrick2(new Vector2d(-58, 25.5), Rotation2d.fromDouble(0.0)),
//        BLbrick3(new Vector2d(-48, 25.5), Rotation2d.fromDouble(0.0)),
        YLbrick1(new Vector2d(-68, -25.5), Rotation2d.fromDouble(0.0)),
        YLbrick2(new Vector2d(-58, -25.5), Rotation2d.fromDouble(0.0)),
        YLbrick3(new Vector2d(-48, -25.5), Rotation2d.fromDouble(0.0)),
//        YRbrick1 (new Vector2d(48, 25.5), Rotation2d.fromDouble(0.0)),
//        YRbrick2(new Vector2d(58, 25.5), Rotation2d.fromDouble(0.0)),
//        YRbrick3(new Vector2d(68, 25.5), Rotation2d.fromDouble(0.0)),
        RRbrick1(new Vector2d(48, -25.5), Rotation2d.fromDouble(0.0)),
        RRbrick2(new Vector2d(58, -25.5), Rotation2d.fromDouble(0.0)),
        RRbrick3(new Vector2d(68, -25.5), Rotation2d.fromDouble(0.0)),
        // point scoring spots
//        Bbasket(new Vector2d(53, 53), Rotation2d.fromDouble(0.0)),
        Rbasket(new Vector2d(-53, -53), Rotation2d.fromDouble(0.0)),
//        Bzone(new Vector2d(-57, 58), Rotation2d.fromDouble(0.0)),
        Rzone(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),
//        BSpecieminBar(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),
        RSpecieminBar(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),
        // Places around the center area
//        TLwall(new Vector2d(-23.5, 23), Rotation2d.fromDouble(0.0)),
//        TRwall(new Vector2d(23.5, 23), Rotation2d.fromDouble(0.0)),
        BLwall(new Vector2d(-23.5, -23), Rotation2d.fromDouble(0.0)),
        BRwall(new Vector2d(23.5, -23), Rotation2d.fromDouble(0.0)),
//        TLinpoint(new Vector2d(-22, 12.5), Rotation2d.fromDouble(-PI*0)),
//        TRinpoint(new Vector2d(22, 12.5), Rotation2d.fromDouble(-PI*.5)),
        BLinpoint(new Vector2d(-22, -12.5), Rotation2d.fromDouble(-PI*0)),
        BRinpoint(new Vector2d(22, -12.5), Rotation2d.fromDouble(-PI*.5));

        Positions(Vector2d vector, Rotation2d rotation2d) {
                runTo = new RunTo(vector,rotation2d);
    }
    public final RunTo runTo;
}
