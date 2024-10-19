package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDParams;

public class Linkage {
    DcMotor linkage;

    public LinkageState linkageState;

    PIDFcontroller pidFcontroller = new PIDFcontroller(new PIDParams(0,0,0,0));

    public Linkage (HardwareMap hardwareMap){
        linkage = hardwareMap.get(DcMotor.class, "clawFlipper");
    }

    public void update() {

        int linkageEncoder = linkage.getCurrentPosition();
        double power = pidFcontroller.calculate(linkageState.goalPos - linkageEncoder);

        if(linkageState == LinkageState.IDLE){
            power = 0;
        }

        linkage.setPower(power);

    }

    public enum LinkageState {
        VERTICAL(0),
        HORIZONTAL(0.5),
        IDLE(0);

        public final double goalPos;
        LinkageState(double goalPos) {
            this.goalPos = goalPos;
        }
    }
}

