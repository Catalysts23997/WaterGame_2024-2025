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

public class ClawJohn {

    Servo claw;
    Servo armPitch;
    Servo clawPitch;
    ClawRotater clawRotater;

    public ClawState clawState;
    public ArmState armState;

    public ClawJohn(HardwareMap hardwareMap) {
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
    public double clawYaw = 0;
    public double slideLength = 0.0;//todo this var should not need to be here, if gamepad increases it should just increase encoder count by a certain amount

    public void update(double goalDistance) {

        if (goalDistance > 42) {
            goalDistance = 42;
        }

        if (slideDegree < toDegrees(atan((clawLength - slidesFromGround) / goalDistance))) {
            slideDegree = 10 + toDegrees(atan((clawLength - slidesFromGround) / goalDistance));
        }

        AttachmentPositons rotations = getExtension(goalDistance);

        switch (armState) {
            //when the robot is stationary, we want the claw tucked away
            case STATIONARY:
                armPitch.setPosition(0.9);
                clawPitch.setPosition(0.0);
                break;
            //before we lower the claw or after we grab, when going to the submersible, the claw has to be up so we fit over the barrier
            case PREPARED:
                armPitch.setPosition(rotations.armServoRot / 180);
                clawPitch.setPosition(0.0);
                break;
            //once we are collection, we use positions from the calculations above
            case COLLECTING:
                armPitch.setPosition(rotations.armServoRot / 180);
                clawPitch.setPosition(rotations.clawServoRot / 180);
                break;
            //when depositing, go to the servo start position.
            case DEPOSITING:
                armPitch.setPosition(0.0);
                clawPitch.setPosition(0.0);
                break;
        }

        claw.setPosition(clawState.servoPos);

        switch (yawState) {
            case ACTIVE:
                clawRotater.update(clawYaw);
            case STRAIGHT:
                clawRotater.update(-toDegrees(Localizer.pose.heading.toDouble()));
        }
    }

    public AttachmentPositons getExtension(double goalDistance) {

        //todo name X?
        double x = sqrt(goalDistance * goalDistance + (clawLength - slidesFromGround) * (slidesFromGround - clawLength));

        double angleD = toDegrees(atan(goalDistance / (clawLength - slidesFromGround)));
        double angleC = slideDegree - (90 - angleD);
        double v = x * sin(toRadians(angleC)); // todo name you use this two times
        double angleX = toDegrees(asin(v / armLength));
        if ((armLength > v) && (armLength < x) && (angleX < 90)) {
            angleX = 180 - angleX;
        }
        double angleB = 180 - angleX - angleC;

        return new AttachmentPositons(270 - angleD - angleB,
                180 - angleX,
                (armLength * sin(toRadians(angleB))) / sin(toRadians(angleC)));
    }


    public YawState yawState;

    public enum YawState {
        ACTIVE,
        STRAIGHT
    }

}
