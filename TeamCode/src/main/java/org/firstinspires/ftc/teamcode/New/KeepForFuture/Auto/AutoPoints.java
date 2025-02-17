package org.firstinspires.ftc.teamcode.New.KeepForFuture.Auto;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.New.KeepForFuture.Auto.SetDriveTarget;
import org.firstinspires.ftc.teamcode.New.KeepForFuture.Utilities.Poses;


public enum AutoPoints {
    StartYellow(new Vector2d(-19,-66), 0.0),
        Yellow1(new Vector2d(-48, -57.75), 0.0),
    Yellow2(new Vector2d(-58,-57.75), 0.0),
    Yellow3(new Vector2d(-45.37258, -48.37742), Math.PI/4),
    Basket(new Vector2d(-56.85062,-56.85062), 3*Math.PI/4),
    SubmersibleEnd(new Vector2d(-23.75,-10), -Math.PI/2),



    Start(new Vector2d(19,-66), 0.0),
    Specimin1(new Vector2d(9.16667,-53.375), 0.0),
    Sample1(new Vector2d(20.32881,-41.82188), -1.0446040425),
    Player1(new Vector2d(20.32881,-41.82188), -2.37181621996),
    Sample2(new Vector2d(30.42994, -41.99474), -1.03834344965),
    Player2(new Vector2d(30.42994, -41.99474), -2.12738091603),
    Sample3(new Vector2d(39.73853,-40.75964), -1.09987360628),
    Player3(new Vector2d(39.73853,-40.75964), -2.226693418),
    WallIntake(new Vector2d(35.4,-53.375), 0.0),
    WallIntakeFinal(new Vector2d(35.4,-60), 0.0),
    Specimin2(new Vector2d(4.583334,-53.375), 0.0),
    Specimin3(new Vector2d(0,-53.375), 0.0),
    Specimin4(new Vector2d(-4.583334,-53.375), 0.0),
    Specimin5(new Vector2d(-9.16667,-53.375), 0.0),

    Pos1(new Vector2d(38,-15.75), 0.0),
    GotoSample1(new Vector2d(46,-15.75), 0.0),
    PushtoSample1(new Vector2d(48,-60), 0.0),
    GotoSample2(new Vector2d(58,-15.75), 0.0),
    PushtoSample2(new Vector2d(58,-60), 0.0),
    GotoSample3(new Vector2d(66,-15.75), 0.0),
    PushtoSample3(new Vector2d(66,-60), 0.0),

    Pos1Y(new Vector2d(-38,-15.75), 0.0),
    GotoSample1Y(new Vector2d(-46,-15.75), 0.0),
    PushtoSample1Y(new Vector2d(-50,-60), -0.1),
    GotoSample2Y(new Vector2d(-58,-15.75), 0.0),
    PushtoSample2Y(new Vector2d(-58,-60), 0.0),
    GotoSample3Y(new Vector2d(-66,-15.75), 0.0),
    PushtoSample3Y(new Vector2d(-66,-55), 0.0),
    YEnd(new Vector2d(-30,-10), 0.0),



    End(new Vector2d(43,-63), 0.0);



    AutoPoints(Vector2d vector, Double rotation) {
        runToExact = new SetDriveTarget(new Poses(vector,rotation));
    }

    public final SetDriveTarget runToExact;
}
