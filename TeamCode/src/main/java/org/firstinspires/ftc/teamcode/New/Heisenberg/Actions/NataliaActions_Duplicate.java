package org.firstinspires.ftc.teamcode.New.Heisenberg.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

//hello! these actions are already in the Untested file. I realized it might be helpful to have them isolated, so here they are.
// if this file is no longer of use, feel free to delete as its contents are already in the main actions file. --Natalia

public class NataliaActions_Duplicate {
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
}



