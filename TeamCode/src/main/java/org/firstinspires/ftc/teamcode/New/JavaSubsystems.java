package org.firstinspires.ftc.teamcode.New;

public interface JavaSubsystems {
    void update();
    default void updateMotorEncoder(int encoder){}
}
