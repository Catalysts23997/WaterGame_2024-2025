package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Controller;
import org.firstinspires.ftc.teamcode.New.PIDParams;
import org.firstinspires.ftc.teamcode.PIDTuner.Constants;

public class ShoudlerJohn {
    DcMotorEx Shoulder;
    public State state = State.IDLE;
    Constants constants = new Constants();
    public boolean targetReached = false;
    public static double angle = 0.0;
    Controller pidFcontroller = new Controller(new PIDParams(.4, .01, .15, .32));

    public ShoudlerJohn(HardwareMap hardwareMap) {
        Shoulder = hardwareMap.get(DcMotorEx.class, constants.motorName);
        Shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
        Shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Shoulder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Shoulder.setPower(0.0);
    }

    public void update() {
        int encoder = Shoulder.getCurrentPosition();

        if(Math.abs(state.target-angle) < Math.toRadians(45)) {
            pidFcontroller.setParams(new PIDParams(.3, .0, .15, .2));
            if(state == State.SpecimenDeposit) pidFcontroller.setParams(new PIDParams(.6, 0.0, .05, .4));
            if(state== State.SpecimenIntake) pidFcontroller.setParams(new PIDParams(.8, .0, .1, .25));
            if(state == State.SubmersibleIntake) pidFcontroller.setParams(new PIDParams(.5, .0, .08, .2));
        }
        else pidFcontroller.setParams(new PIDParams(.35, .0, .1, .315));
        angle = Math.toRadians(3.0) + encoder * (2 * Math.PI / 1995.0);
        double power = pidFcontroller.calculate(state.target -angle, angle);


//        Log.d("YOO", String.valueOf(power));
//        Log.d("YOO", String.valueOf(encoder));

        targetReached = Math.abs(angle - state.target) < Math.toRadians(20.0);

        if (state == State.IDLE) {
            power = 0;
            targetReached = false;
        }
        Shoulder.setPower(power);
    }

    public enum State {
        SpecimenIntakeHigh(360-131),
        SpecimenIntakeLow(360-123),
        SpecimenDeposit(123),
        SpecimenIntake(360-119),
        HPdrop(360-100),
        BasketDeposit(130),
        SubmersibleIntake(56),
        IDLE(0.0);
        public final double target;
        State(double Target) {
            this.target = Math.toRadians(Target);
        }
    }

    /*
    public enum State {
        SpecimenDepositPrep(Constants.angleRanges.get(1), Constants.params.get(1)),
        SpecimenDeposit(Constants.angleRanges.get(2), Constants.params.get(2)),
        SpecimenIntake(Constants.angleRanges.get(3), Constants.params.get(3)),
        HPdrop(Constants.angleRanges.get(0), Constants.params.get(0)),
        BasketDeposit(Constants.angleRanges.get(4), Constants.params.get(4)),
        SubmersibleIntake(Constants.angleRanges.get(5), Constants.params.get(5)),
        IDLE(new AngleRange(Math.toRadians(3.0),Math.toRadians(3.0)), new PIDFParams(0.0,0.0,0.0,0.0));
        public final AngleRange target;
        public final PIDFParams params;

        State(AngleRange angleRange, PIDFParams params) {
            this.target = angleRange;
            this.params = params;
        }
    }

     */
}

