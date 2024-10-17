package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDParams;

public class ClawJohn  {
    Servo leftClaw;
    Servo rightClaw;
    DcMotor linkage;

    public ClawState clawState;
    public LinkageState linkageState;

    PIDFcontroller pidFcontroller = new PIDFcontroller(new PIDParams(0,0,0,0));

    public ClawJohn(HardwareMap hardwareMap){
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        //isaballs was here
        linkage = hardwareMap.get(DcMotor.class, "clawFlipper");
    }

    public void update() {
        leftClaw.setPosition(clawState.servoPos);
        rightClaw.setPosition(-clawState.servoPos);

        int linkageEncoder = linkage.getCurrentPosition();
        double power = pidFcontroller.calculate(linkageState.goalPos - linkageEncoder);

        if(linkageState == LinkageState.IDLE){
            power = 0;
        }

        linkage.setPower(power);

    }

    public enum ClawState {
        CLOSED(0),
        OPEN(0.25);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
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
