package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import static org.firstinspires.ftc.teamcode.New.Utilities.ArmLengthKt.moveArmToPoint;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Slides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ColorSensor;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Linkage;

public class Heisenberg {
    ArmServos armServos;
    LinearSlides linearSlides;
    Linkage linkage;
    Claw claw;
    ClawRotater clawRotater;
    ColorSensor colorSensor;

    AttachmentPositions attachmentPositons;
    double maxExtension = 40.0;
    double slideExtension = maxExtension;
    double clawRotatorAngle = 0.0;

    public void update() {
        armServos.update(attachmentPositons.armAngle, attachmentPositons.clawAngle);
        linkage.update(attachmentPositons.linkageAngle);
        linearSlides.update(slideExtension);
        claw.update();
        clawRotater.update(clawRotatorAngle);
    }

    public Heisenberg(HardwareMap hardwareMap) {
        armServos = new ArmServos(hardwareMap);
        linearSlides = new LinearSlides(hardwareMap);
        linkage = new Linkage(hardwareMap);
        claw = new Claw(hardwareMap);
        clawRotater = new ClawRotater(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    public Action template = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            attachmentPositons = moveArmToPoint(5.0, 40.0, Math.toRadians(180), slideExtension);
            armServos.armState = ArmServos.ArmState.INPUT;
            linkage.linkageState = Linkage.LinkageState.INPUT;
            claw.clawState = Claw.ClawState.CLOSED;
            clawRotatorAngle = 0.0;
            slideExtension  =50.0;
            return false;
        }
    };


    /*public Action grabFromSubmersible = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //go to the specimen with positions class

            attachmentPositons = moveArmToPoint(5.0, 40.0, Math.toRadians(0), slideExtension);

            new ParallelAction(
                    Positions.BackRightSubmersableIntake.runToExact,
                    claw.clawState = Claw.ClawState.OPEN,
                    linkage.linkageState = Linkage.LinkageState.INPUT,
                     slideExtension = 0.0,

                    new SequentialAction(

                            //colorsensor detection of pixel
                            claw.clawState = Claw.ClawState.CLOSED,
                            slideExtension = 0.0,
                            linkage.linkageState = Linkage.LinkageState.IDLE,


                            )

                    );

            return false;


        }
    };
*/
    public Action Hang = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            attachmentPositons = moveArmToPoint(5.0, 40.0, Math.toRadians(0), slideExtension);
            claw.clawState = Claw.ClawState.OPEN;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            //requires gamepad or touch sensor input to close as this is only performed in teleop
            //as a result i'm setting this up to put the robot in position, not to close it automatically
            //sequential action of once slides reach position then claw close???
            //otherwise we could just have claw manually close
            clawRotatorAngle = 90.0;
            slideExtension = 50.0;
            // i don't know how far to extend the slides
            // i'll fix this later





            return false;
        }
    };


    public Action grabFromGround = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //shoulder
            //claw rotator
            //Not sure which exact servo position to use
//            clawRotator.state = clawRotator.State.HalfWay;
            return false;
        }
    };

    public Action raiseandHangSpecimen = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //move shoulder to be able to keep specimen straight
            //raise slides slightly above bar height
            //rotate claw to halfway (so hook can attach onto the bar)
            //lower slides to put specimen on the bar
            //claw - open to let go of specimen
            return false;
        }
    };

    public Action raisesanddropspixel = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {


            //raiselinearslides
            //shoulder
            //drop using wrist
            //claw release


            return false;
        }
    };
    public Action idle = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            attachmentPositons = moveArmToPoint(5.0, 40.0, Math.toRadians(0), slideExtension);
            claw.clawState = Claw.ClawState.CLOSED;
            linkage.linkageState = Linkage.LinkageState.IDLE;
            slideExtension = 0.0;
            clawRotatorAngle= 90.0;



            return false;
        }
    };

}