package org.firstinspires.ftc.teamcode.New.Competition.Actions;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.New.Competition.subsystems.RunToExact;

public enum Positions {
    // Blue side Bricks
    Blueleftbrick1 (new Vector2d(-68, 25.5), 0.0),
    Blueleftbrick2(new Vector2d(-58, 25.5), 0.0),
    Blueleftbrick3(new Vector2d(-48, 25.5), 0.0),

    YellowRightbrick1 (new Vector2d(48, 25.5), 0.0),
    YellowRightbrick2(new Vector2d(58, 25.5), 0.0),
    YellowRightbrick3(new Vector2d(68, 25.5),0.0),


    // Red side Bricks
    YellowLeftbrick1(new Vector2d(-68, -25.5), 0.0),
    YellowLeftbrick2(new Vector2d(-58, -25.5), 0.0),
    YellowLeftbrick3(new Vector2d(-48, -25.5), 0.0),

    RedRightbrick1(new Vector2d(48, -25.5), 0.0),
    RedRightbrick2(new Vector2d(58, -25.5), 0.0),
    RedRightbrick3(new Vector2d(68, -25.5), 0.0),

    // point scoring spots
    Bluebasket(new Vector2d(53, 53),0.0),
    Redbasket(new Vector2d(-53, -53), 0.0),

    BlueHumanIntake(new Vector2d(-57, 58), 0.0),
    RedHumanIntake(new Vector2d(57, -58), 0.0),

    BlueSpecieminBar(new Vector2d(57, -58), 0.0),
    RedSpecieminBar(new Vector2d(57, -58), 0.0),

    // Places around the center area
    TopLeftSidePanel(new Vector2d(-23.5, 23), 0.0),
    TopRightSidePanel(new Vector2d(23.5, 23), 0.0),
    BackLeftSidePanel(new Vector2d(-23.5, -23), 0.0),
    BackRightSidePanel(new Vector2d(23.5, -23), 0.0),

    // The center Submersable intake Points
    TopLeftSubmersableIntake(new Vector2d(-22, 12.5), -PI*0),
    TopRightSubmersableIntake(new Vector2d(22, 12.5), -PI*.5),
    BackLeftSubmersableIntake(new Vector2d(-22, -12.5), -PI * 0),
    BackRightSubmersableIntake(new Vector2d(22, -12.5), -PI * .5),
    Test(new Vector2d(0, 30), PI/2),
    Test2(new Vector2d(-10, 20), -PI/2),
    Test3(new Vector2d(5, 15), PI/2),
    Test4(new Vector2d(0, 0), 0.0),
    Test5(new Vector2d(-2, 45), PI);

    Positions(Vector2d vector, Double rotation) {
        runToNearest = new RunToNearest(vector);
        runToExact = new RunToExact(vector, rotation);
    }

    public final RunToNearest runToNearest;
    public final RunToExact runToExact;
}
