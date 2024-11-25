package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Slides;

public class CollectionOfAttachments {
    Servo claw;
    Servo armPitch;
    Servo clawPitch;
    public ClawRotater clawRotater;

    public ClawState clawState;
    public ArmState armState;

    public CollectionOfAttachments(HardwareMap hardwareMap){
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


    public static double maxExtension = 37.7716535;
    public static double minExtension = maxExtension - 33;
    public static double slideDegree = 11.3809843;
    public double slideLength = 0;
    public AttachmentPositons attachmentPositons;



    public void update(double goalDistance){

        attachmentPositons = Slides.INSTANCE.linearSlideExtension(goalDistance);

        switch (armState){
            //when the robot is stationary, we want the claw tucked away
            case STATIONARY: armPitch.setPosition(0.9);
                clawPitch.setPosition(0.0);
                break;
            //before we lower the claw or after we grab, when going to the submersible, the claw has to be up so we fit over the barrier
            case PREPARED:
                armPitch.setPosition(attachmentPositons.armServoRot/180);
                clawPitch.setPosition(0.0);
                break;
            //once we are collection, we use positions from the calculations above
            case COLLECTING:
                armPitch.setPosition(attachmentPositons.armServoRot/180);
                clawPitch.setPosition(attachmentPositons.clawServoRot/180);
                break;
            //when depositing, go to the servo start position.
            case DEPOSITING: armPitch.setPosition(0.0);
            clawPitch.setPosition(0.0);
            break;
        }

        claw.setPosition(clawState.servoPos);

        clawRotater.update();
    }
}
