package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ClawRotater;

import java.util.Optional;

public class ClawJohn  {
    Servo claw;
    Servo armPitch;
    Servo clawPitch;
    ClawRotater clawRotater;

    public ClawState clawState;
    public ArmState armState;

    public ClawJohn(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "Claw");
        clawRotater = new ClawRotater(hardwareMap);
    }

    public enum ClawState {
        CLOSED(0),
        OPEN(0.25);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public enum ArmState {
        //note 1 = 180 degrees.
        COLLECTING,
        //when prepared, the arm is
        PREPARED,
        //when stationary, the claw will be down right in front of the robot
        STATIONARY,
        //I used the depositing position as the starting point 0,0
        DEPOSITING
    }

    double clawLength = 6.5826772;
    double armLength = 6.1338583;
    double slidesFromGround = 2.976378;
    public double slideDegree = 11.3809843;
    double maxExtension = 37.7716535;
    double clawServoRot;
    double armServoRot;
    public double slideLength = 0;

    public double clawYaw = 0;

    public void update(double goalDistance){

        if (goalDistance > 42){
            goalDistance = 42;
        }

        double x = sqrt(goalDistance*goalDistance + (clawLength-slidesFromGround)*(slidesFromGround-clawLength));

        if (slideDegree<toDegrees(atan((clawLength-slidesFromGround)/goalDistance))){
            slideDegree = 10 + toDegrees(atan((clawLength-slidesFromGround)/goalDistance));
        }

        double angleD = toDegrees(atan(goalDistance/(clawLength-slidesFromGround)));
        double angleC = slideDegree-(90-angleD);
        double angleX = toDegrees(asin((x*sin(toRadians(angleC)))/ armLength));
        if ((armLength > x*sin(toRadians(angleC))) && (armLength < x) && (angleX<90)){
            angleX = 180 - angleX;
        }
        double angleB = 180 - angleX - angleC;

        clawServoRot = 270 - angleD - angleB;
        armServoRot = 180 - angleX;
        slideLength = (armLength * sin(toRadians(angleB)))/sin(toRadians(angleC));

        switch (armState){
            //when the robot is stationary, we want the claw tucked away
            case STATIONARY: armPitch.setPosition(0.9);
            clawPitch.setPosition(0.0);
            break;
            //before we lower the claw or after we grab, when going to the submersible, the claw has to be up so we fit over the barrier
            case PREPARED: armPitch.setPosition(armServoRot/180);
            clawPitch.setPosition(0.0);
            break;
            //once we are collection, we use positions from the calculations above
            case COLLECTING: armPitch.setPosition(armServoRot/180);
            clawPitch.setPosition(clawServoRot/180);
            break;
            //when depositing, go to the servo start position.
            case DEPOSITING: armPitch.setPosition(0.0);
            clawPitch.setPosition(0.0);
            break;
        }

        claw.setPosition(clawState.servoPos);

        switch (yawState){
            case ACTIVE: clawRotater.update(clawYaw);
            case STRAIGHT: clawRotater.update(-toDegrees(Localizer.pose.heading.toDouble()));
        }
    }

    public YawState yawState;

    public enum YawState {
        ACTIVE,
        STRAIGHT
    }
}
