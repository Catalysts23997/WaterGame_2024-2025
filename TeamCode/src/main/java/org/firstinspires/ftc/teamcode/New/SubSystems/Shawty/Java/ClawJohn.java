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

public class ClawJohn  {
    Servo claw;
    Servo armPitch;
    Servo clawPitch;
    public ClawRotater clawRotater;

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
    public double maxLinkageDegree = 11.3809843;
    public double slideDegree = maxLinkageDegree;
    double maxExtension = 37.7716535;
    double clawServoRot;
    double armServoRot;
    public double slideLength = 0;

    public double clawYaw = 0;

    public void getExtension(double goalDistance){
        if (goalDistance > 42){
            goalDistance = 42;
        }

        double hypotenuse = sqrt(goalDistance*goalDistance + (clawLength-slidesFromGround)*(slidesFromGround-clawLength));

        if (slideDegree<toDegrees(atan((clawLength-slidesFromGround)/goalDistance))){
            slideDegree = 10 + toDegrees(atan((clawLength-slidesFromGround)/goalDistance));
        }
        else {
            slideDegree = maxLinkageDegree;
        }

        double angleOppGround = toDegrees(atan(goalDistance/(clawLength-slidesFromGround)));
        double angleOppArm = slideDegree-(90- angleOppGround);
        double insideArmAngle = toDegrees(asin((hypotenuse *sin(toRadians(angleOppArm)))/ armLength));

        //if the arm length can possibly make two triangles because of ASS and if it is currently chosing the longer one, switch to the shortest triangle
        if ((armLength > hypotenuse *sin(toRadians(angleOppArm))) && (armLength < hypotenuse) && (insideArmAngle <90)){
            insideArmAngle = 180 - insideArmAngle;
        }
        double angleOppSlides = 180 - insideArmAngle - angleOppArm;

        clawServoRot = 270 - angleOppGround - angleOppSlides;
        armServoRot = 180 - insideArmAngle;
        slideLength = (armLength * sin(toRadians(angleOppSlides)))/sin(toRadians(angleOppArm));
    }

    public void update(double goalDistance, double rotaterAngle){

        switch (armState){
            //when the robot is stationary, we want the claw tucked away
            case STATIONARY: armPitch.setPosition(0.9);
                clawPitch.setPosition(0.0);
                break;
            //before we lower the claw or after we grab, when going to the submersible, the claw has to be up so we fit over the barrier
            case PREPARED: getExtension(goalDistance);
                armPitch.setPosition(armServoRot/180);
                clawPitch.setPosition(0.0);
                break;
            //once we are collection, we use positions from the calculations above
            case COLLECTING: getExtension(goalDistance);
                armPitch.setPosition(armServoRot/180);
                clawPitch.setPosition(clawServoRot/180);
                break;
            //when depositing, go to the servo start position.
            case DEPOSITING: armPitch.setPosition(0.0);
            clawPitch.setPosition(0.0);
            break;
        }

        claw.setPosition(clawState.servoPos);

        clawRotater.update(rotaterAngle);
    }
}
