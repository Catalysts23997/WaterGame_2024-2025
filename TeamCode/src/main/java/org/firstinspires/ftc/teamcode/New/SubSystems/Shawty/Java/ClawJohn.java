package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDParams;

public class ClawJohn  {
    Servo leftClaw;
    Servo rightClaw;
    DcMotor clawExtender;
    Servo clawFlipper;

    PIDParams extenderParams = new PIDParams(0,0,0,0);
    PIDFcontroller extenderPID = new PIDFcontroller(extenderParams);

    public ClawState clawState;
    public FlipperState flipperState;
    public ExtenderState extenderState;
    int target;
    public int maxExtension;
    public int maxRetaction;
    public int stationaryGoal;

    public ClawJohn(HardwareMap hardwareMap){
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        clawExtender = hardwareMap.get(DcMotor.class, "clawExtender");
        clawFlipper = hardwareMap.get(Servo.class, "clawFlipper");
    }

    public void update(int extendingGoal) {

        if(extendingGoal>maxExtension) {
            extendingGoal = maxExtension;
        }

        if(extendingGoal<maxRetaction) {
            extendingGoal = maxRetaction;
        }

        switch (extenderState){
            case RETRACTED: target = stationaryGoal; break;
            case EXTENDING: target = extendingGoal; break;
        }

        double extenderEncoder = clawExtender.getCurrentPosition();

        double extenderPower = extenderPID.calculate(target - extenderEncoder);
        if (extenderState == ExtenderState.IDLE){
            extenderPower = 0;
        }

        clawExtender.setPower(extenderPower);

        leftClaw.setPosition(clawState.servoPos);
        rightClaw.setPosition(-clawState.servoPos);
        clawFlipper.setPosition(flipperState.servoPos);
    }

    public enum ClawState {
        CLOSED(0),
        OPEN(0.25);

        public final double servoPos;
        ClawState(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public enum FlipperState {
        DEPOSIT(0),
        COLLECTION(0.5);

        public final double servoPos;
        FlipperState(double servoPos) {
            this.servoPos = servoPos;
        }
    }

    public enum ExtenderState {
        EXTENDING,
        IDLE,
        RETRACTED
    }
}
