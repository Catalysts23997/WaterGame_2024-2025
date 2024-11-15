package org.firstinspires.ftc.teamcode.New.Competition.Actions;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

public enum Positions {
    // Top Bricks
    Blueleftbrick1 (new Vector2d(-68, 25.5), Rotation2d.fromDouble(0.0)),
    Blueleftbrick2(new Vector2d(-58, 25.5), Rotation2d.fromDouble(0.0)),
    Blueleftbrick3(new Vector2d(-48, 25.5), Rotation2d.fromDouble(0.0)),

    YellowRightbrick1 (new Vector2d(48, 25.5), Rotation2d.fromDouble(0.0)),
    YellowRightbrick2(new Vector2d(58, 25.5), Rotation2d.fromDouble(0.0)),
    YellowRightbrick3(new Vector2d(68, 25.5), Rotation2d.fromDouble(0.0)),

    //Bottom Bricks
    YellowLeftbrick1(new Vector2d(-68, -25.5), Rotation2d.fromDouble(0.0)),
    YellowLeftbrick2(new Vector2d(-58, -25.5), Rotation2d.fromDouble(0.0)),
    YellowLeftbrick3(new Vector2d(-48, -25.5), Rotation2d.fromDouble(0.0)),

    RedRightbrick1(new Vector2d(48, -25.5), Rotation2d.fromDouble(0.0)),
    RedRightbrick2(new Vector2d(58, -25.5), Rotation2d.fromDouble(0.0)),
    RedRightbrick3(new Vector2d(68, -25.5), Rotation2d.fromDouble(0.0)),

    // point scoring spots
    Bluebasket(new Vector2d(53, 53), Rotation2d.fromDouble(0.0)),
    Redbasket(new Vector2d(-53, -53), Rotation2d.fromDouble(0.0)),

    BlueHumanIntake(new Vector2d(-57, 58), Rotation2d.fromDouble(0.0)),
    RedHumanIntake(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),

    BlueSpecieminBar(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),
    RedSpecieminBar(new Vector2d(57, -58), Rotation2d.fromDouble(0.0)),

    // Places around the center area
    TopLeftSidePanel(new Vector2d(-23.5, 23), Rotation2d.fromDouble(0.0)),
    TopRightSidePanel(new Vector2d(23.5, 23), Rotation2d.fromDouble(0.0)),
    BackLeftSidePanel(new Vector2d(-23.5, -23), Rotation2d.fromDouble(0.0)),
    BackRightSidePanel(new Vector2d(23.5, -23), Rotation2d.fromDouble(0.0)),

    // The center Submersable intake Points
    TopLeftSubmersableIntake(new Vector2d(-22, 12.5), Rotation2d.fromDouble(-PI*0)),
    TopRightSubmersableIntake(new Vector2d(22, 12.5), Rotation2d.fromDouble(-PI*.5)),
    BackLeftSubmersableIntake(new Vector2d(-22, -12.5), Rotation2d.fromDouble(-PI * 0)),
    BackRightSubmersableIntake(new Vector2d(22, -12.5), Rotation2d.fromDouble(-PI * .5));

    Positions(Vector2d vector, Rotation2d rotation2d) {
        runToNearest = new RunToNearest(vector);
        runToExact = new RunToExact(vector, rotation2d);
    }

    public final RunToNearest runToNearest;
    public final RunToExact runToExact;
}
