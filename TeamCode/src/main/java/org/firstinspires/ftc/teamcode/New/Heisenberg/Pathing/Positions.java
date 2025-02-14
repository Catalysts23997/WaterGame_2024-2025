package org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.New.Utilities.Poses;


public enum Positions {
    // Blue side Bricks
//    Blueleftbrick1 (new Vector2d(-68, 25.5), 0.0),
//    Blueleftbrick2(new Vector2d(-58, 25.5), 0.0),
//    Blueleftbrick3(new Vector2d(-48, 25.5), 0.0),
//
//    YellowRightbrick1 (new Vector2d(48, 25.5), 0.0),
//    YellowRightbrick2(new Vector2d(58, 25.5), 0.0),
//    YellowRightbrick3(new Vector2d(68, 25.5),0.0),
//
//
//    // Red side Bricks
//    YellowLeftbrick1(new Vector2d(-68, -25.5), 0.0),
//    YellowLeftbrick2(new Vector2d(-58, -25.5), 0.0),
//    YellowLeftbrick3(new Vector2d(-48, -25.5), 0.0),
//
//    RedRightbrick1(new Vector2d(48, -25.5), 0.0),
//    RedRightbrick2(new Vector2d(58, -25.5), 0.0),
//    RedRightbrick3(new Vector2d(68, -25.5), 0.0),
//
//    // point scoring spots
//    Bluebasket(new Vector2d(53, 53),0.0),
//    Redbasket(new Vector2d(-53, -53), 0.0),
//
//    BlueHumanIntake(new Vector2d(-57, 58), 0.0),
//    RedHumanIntake(new Vector2d(57, -58), 0.0),
//
//    BlueSpecieminBar(new Vector2d(0, 24.5), 0.0),
//    RedSpecieminBar(new Vector2d(0, -24.5), 0.0),
//
//    // Places around the center area
//    TopLeftSidePanel(new Vector2d(-23.5, 23), 0.0),
//    TopRightSidePanel(new Vector2d(23.5, 23), 0.0),
//    BackLeftSidePanel(new Vector2d(-23.5, -23), 0.0),
//    BackRightSidePanel(new Vector2d(23.5, -23), 0.0),
//
//    // The center Submersable intake Points
//    TopLeftSubmersableIntake(new Vector2d(-22, 12.5), -PI*0),
//    TopRightSubmersableIntake(new Vector2d(22, 12.5), -PI*.5),
//    BackLeftSubmersableIntake(new Vector2d(-22, -12.5), -PI * 0),
//    BackRightSubmersableIntake(new Vector2d(22, -12.5), -PI * .5),
//
//    //new points
//    RedBrickMiddle(new Vector2d( 58,-40), -PI * 0),
//    RedBrickMiddleLeft(new Vector2d( 58,-40), PI * .75),
//    RedBrickMiddleRight(new Vector2d( 58,-40), PI *.25),
//    CornerOfRedZone(new Vector2d( 36,-70), -PI * 0),
//
//
//
//    //Not my point's blame arya
//    Test(new Vector2d(0, 30), 0.0),
//    Test2(new Vector2d(-30.0, 10.0), -PI/2),
//    Test3(new Vector2d(-33, 40), 0.0),
//    Test4(new Vector2d(-33, 20), 0.0),
////    Test4(new Vector2d(0, 0), 0.0),
//
//    blah(new Vector2d(15, 15), 0.0),
//    Test5(new Vector2d(-2, 45), PI),
//
//    FirstSample(new Vector2d(19.7, -41.75), 1.05622859),
//    HP(new Vector2d(19.7, -41.75), 1.9),
//    SecondSample(new Vector2d(19.7, -41.75), 1.05622859),
//    ThirdSample(new Vector2d(19.7, -41.75), 1.05622859),
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
    WallIntake(new Vector2d(35.4,-53.375), Math.PI),
    Specimin2(new Vector2d(4.583334,-53.375), 0.0),
    Specimin3(new Vector2d(0,-53.375), 0.0),
    Specimin4(new Vector2d(-4.583334,-53.375), 0.0),
    Specimin5(new Vector2d(-9.16667,-53.375), 0.0),
    End(new Vector2d(43,-63), 0.0);



    Positions(Vector2d vector, Double rotation) {
        runToNearest = new RunToNearest(vector);
        runToExact = new RunToExact(new Poses(vector,rotation));
    }

    public final RunToNearest runToNearest;
    public final RunToExact runToExact;
}
