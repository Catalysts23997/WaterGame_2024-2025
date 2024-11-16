package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import android.util.Log;

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
    Controller pidFcontroller = new Controller(new PIDParams(2.2, 0.6363758070970078, 0.4, 0.7522356071054006));

    public ShoudlerJohn(HardwareMap hardwareMap) {
        Shoulder = hardwareMap.get(DcMotorEx.class, constants.motorName);
        Shoulder.setDirection(DcMotorSimple.Direction.REVERSE);
        Shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Shoulder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Shoulder.setPower(0.0);
    }

    public void update() {
        int encoder = Shoulder.getCurrentPosition();

        angle = constants.armAngle.findAngle(encoder);
        double power = pidFcontroller.calculate(state.target -angle, angle);

        Log.d("YOO", String.valueOf(power));
        Log.d("YOO", String.valueOf(encoder));

        targetReached = Math.abs(angle - state.target) < Math.toRadians(5.0);

        if (state == State.IDLE) {
            power = 0;
            targetReached = false;
        }
        Shoulder.setPower(power);
    }

    public enum State {
        SpecimenDepositPrep(130),
        SpecimenDeposit(115),
        SpecimenIntake(360-145),
        HPdrop(360-120),
        BasketDeposit(107),
        SubmersibleIntake(50),
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

