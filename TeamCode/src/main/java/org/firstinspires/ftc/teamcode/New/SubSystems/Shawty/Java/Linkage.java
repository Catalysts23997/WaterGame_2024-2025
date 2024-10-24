package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDParams;

public class Linkage {
    DcMotor linkage;

    public LinkageState linkageState;
    double goalPos;

    PIDFcontroller pidFcontroller = new PIDFcontroller(new PIDParams(0,0,0,0));

    public Linkage (HardwareMap hardwareMap){
        linkage = hardwareMap.get(DcMotor.class, "clawFlipper");
    }

    //TODO convert degrees to encoder ticks.
    public void update(double Goal) {
        switch (linkageState){
            case VERTICAL: goalPos = 0; break;
            case HORIZONTAL: goalPos = Goal; break;
        }


        int linkageEncoder = linkage.getCurrentPosition();
        double power = pidFcontroller.calculate(goalPos - linkageEncoder);

        if(linkageState == LinkageState.IDLE){
            power = 0;
        }

        linkage.setPower(power);

    }

    public enum LinkageState {
        VERTICAL,
        HORIZONTAL,
        IDLE
    }
}

