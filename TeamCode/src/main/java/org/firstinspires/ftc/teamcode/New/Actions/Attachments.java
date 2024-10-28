package org.firstinspires.ftc.teamcode.New.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Java.ClawRotater;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.ClawJohn;
import org.firstinspires.ftc.teamcode.New.SubSystems.Bromine.Kotlin.ColorSensor;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.LinearSlides;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java.Linkage;

public class Attachments {
    public ClawJohn clawJohn;
    public LinearSlides verticalSlides;
    public Linkage linkage;
    public ColorSensor colorSensor;

    public Attachments(HardwareMap hardwareMap){
        verticalSlides = new LinearSlides(hardwareMap);
        clawJohn = new ClawJohn(hardwareMap);
        linkage = new Linkage(hardwareMap);
        colorSensor = new ColorSensor(hardwareMap);
    }

    public void init(){
        verticalSlides.state = LinearSlides.State.STATIONARY;
        clawJohn.clawState = ClawJohn.ClawState.CLOSED;
        linkage.linkageState = Linkage.LinkageState.VERTICAL;
        verticalSlides.setTargets(5000,4000,3000,100,0);
    }

    public Gamepad gamepad2;

    double intakeDistance = 15;

    double clawRotaterAngle;

    //gamepad updater for manual intakes and deposits. //todo finalize gamepad values and comment what they do

    //for an auto update, just make the gamepad null.
    public void update(Gamepad gamepad2){
        this.gamepad2 = gamepad2;

        clawJohn.update(intakeDistance, clawRotaterAngle);

        verticalSlides.updateTarget(3, (int) clawJohn.slideLength);
        verticalSlides.update();

        linkage.update(clawJohn.slideDegree);
    }

    //TODO make a way to update the auto condition, most likely make it if the robot has reached its destination
    boolean autoCondition;

    public class ManualDeposit implements Action{
        boolean isAuto;

        public ManualDeposit(boolean isAuto){
            this.isAuto = isAuto;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //set all parts of the robot to be ready to release in the basket
            clawJohn.clawState = ClawJohn.ClawState.CLOSED;
            clawJohn.armState = ClawJohn.ArmState.DEPOSITING;
            clawJohn.clawRotater.state = ClawRotater.State.ZERO;
            linkage.linkageState = Linkage.LinkageState.VERTICAL;
            verticalSlides.state = LinearSlides.State.BASKET;

            //once the gamepad is pressed when is tele or it is Auto and the auto condition is met,
            // while the slides are at the top, release the game piece and reset
            if ((verticalSlides.hasReached()) && ((isAuto && autoCondition) || (!isAuto && gamepad2.left_trigger > 0.5))){
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                verticalSlides.state = LinearSlides.State.STATIONARY;
                clawJohn.armState = ClawJohn.ArmState.STATIONARY;
                return false;
            }
            else {
                return true;
            }
        }
    }

    //Action to create an object of the class
    public Action manualDeposit(boolean isAuto){
        return new ManualDeposit(isAuto);
    }



    public class ManualIntake implements Action{
        boolean Grabbed = false;
        boolean grabbingFromGround;
        boolean isAuto;

        //grabing from ground variable is used to distinguish grabbing yellow from the ground versus the sub

        public ManualIntake(boolean grabbingFromGround, boolean isAuto){
            this.grabbingFromGround = grabbingFromGround;
            this.isAuto = isAuto;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            //this is manual, update the intake distance based on a joystick input
            if(isAuto){
                //todo replace 10 with the camera distance calculation
                intakeDistance = 10;
            }
            else {
                intakeDistance += (-0.1 * gamepad2.left_stick_y);
            }

            //the color sensor sees the object to intake, switch this variable to true
            //gamepad is a backup to force close it
            if(colorSensor.checkForRecognition() == ColorSensor.Recognition.IN || gamepad2.right_trigger > 0.5){
                Grabbed = true;
            }

            if (Grabbed) {
                if (!grabbingFromGround) {
                    //if we are grabbing from the sub, close the claw then retract the slides while still horizontal, to not hit the  bars
                    linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
                    clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                    clawJohn.armState = ClawJohn.ArmState.PREPARED;
                    verticalSlides.state = LinearSlides.State.STATIONARY;
                    verticalSlides.update();
                    //once the slides fully retract, the everything can reset.
                    if (verticalSlides.hasReached()) {
                        linkage.linkageState = Linkage.LinkageState.VERTICAL;
                        clawJohn.armState = ClawJohn.ArmState.STATIONARY;
                        clawJohn.clawRotater.state = ClawRotater.State.ZERO;
                        return false;
                    }
                    else {
                    return true;
                    }
                }
                //if we are grabbing from the ground, this is much easier, just grab and reset all parts of the robot.
                else {
                    clawJohn.clawState = ClawJohn.ClawState.CLOSED;
                    verticalSlides.state = LinearSlides.State.STATIONARY;
                    linkage.linkageState = Linkage.LinkageState.VERTICAL;
                    clawJohn.clawRotater.state = ClawRotater.State.ZERO;
                    return false;
                }
            }
            //before we detect the piece, we want the claw to be open and to be intaking
            else {
                clawJohn.clawState = ClawJohn.ClawState.OPEN;
                linkage.linkageState = Linkage.LinkageState.HORIZONTAL;
                verticalSlides.state = LinearSlides.State.INTAKE;
                //if we grab from the ground, just have the claw lowered right away and have the claw always adjust to face the same way as the pieces
                if (grabbingFromGround) {
                    clawJohn.clawRotater.state = ClawRotater.State.ADJUSTING;
                    clawJohn.armState = ClawJohn.ArmState.COLLECTING;
                }
                //if were not grabbing from the ground and it has reached the distance, the claw can lower and the yaw angle of the claw will change based on input
                else if (verticalSlides.hasReached()) {
                    clawJohn.clawRotater.state = ClawRotater.State.INPUT;
                    clawJohn.armState = ClawJohn.ArmState.COLLECTING;
                }
                //if were not grabbing from the ground and the slides haven't extended yet, have the claw up so it doesn't hit the sub
                else{
                    clawJohn.clawRotater.state = ClawRotater.State.INPUT;
                    clawJohn.armState = ClawJohn.ArmState.PREPARED;
                }
                return true;
            }
        }
    }

    //create an instance of the Action.
    public Action manualIntake(boolean grabbingFromGround, boolean isAuto){
        return new ManualIntake(grabbingFromGround, isAuto);
    }

}
