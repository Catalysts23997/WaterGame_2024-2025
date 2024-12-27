package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Pathing.Positions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrist;
import org.firstinspires.ftc.teamcode.New.Slides;

public class UnTested {


    //actions
    /*

    Grab from ground
    Grab from Submersible
    Raise and Hang the Specimen
    Raise and Drop in Basket
//me
    Idle





     */


    public Action grabFromGround = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {



            // shoulder
            //claw rotator


            return false;
        }
    };


    public Action grabFromSubmersible= new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
           //go to the specimen with positions class
            //linkage----> horizontal
            //slides----> intake
            //claw ---> open
            //sequential action for this
             //color sensor if loop: if red, yellow, or blue detected, and distance value is good
                // close claw. either put this in an if or while loop dependent on color sensor
             //slides----> idle
            //linkage---> idle





            return false;
        }
    };
    public Action Hang = new Action() {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

//            LinearSlides linearSlides = new LinearSlides(hardwareMap);
//            linearSlides.State = LinearSlides.State.HANG;
//            Linear slides-----> hang
//            claw----->open
            //seqential action of once slides reach position then claw close???
            //otherwise we could just have claw manually close
            // fix this later! 𓆉




            return false;
        }
    };



}
