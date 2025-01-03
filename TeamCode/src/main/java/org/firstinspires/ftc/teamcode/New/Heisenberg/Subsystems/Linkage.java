package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Utilities.Controller;
import org.firstinspires.ftc.teamcode.New.Utilities.PIDParams;


public class Linkage {
    DcMotor linkage;

    public LinkageState linkageState;
    double goalPos;
    final double ticksPerRev = 28;

    Controller PIDcontroller = new Controller(new PIDParams(0,0,0,0));

    public Linkage (HardwareMap hardwareMap){
        linkage = hardwareMap.get(DcMotor.class, "clawFlipper");
    }

    public void init() {
        linkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void update(double goalDegrees) {

        double goalTicks = (goalDegrees/360) * ticksPerRev;

        switch (linkageState){
            //7 ticks = 0.25 revolutions = 90 degrees, so straight up
            case VERTICAL: goalPos = 7; break;
            case INPUT: goalPos = goalTicks; break;
        }


        int linkageEncoder = linkage.getCurrentPosition();
        double power = PIDcontroller.calculate(goalPos ,linkageEncoder);

        if(linkageState == LinkageState.IDLE){
            power = 0;
        }

        linkage.setPower(power);

    }

    public enum LinkageState {
        VERTICAL,
        INPUT,
        IDLE
    }
}

