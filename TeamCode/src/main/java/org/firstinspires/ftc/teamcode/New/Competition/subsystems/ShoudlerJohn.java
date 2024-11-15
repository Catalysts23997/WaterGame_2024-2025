package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.PIDTuner.Constants;

import ArmSpecific.ArmAngle;
import CommonUtilities.AngleRange;
import CommonUtilities.PIDFParams;

public class ShoudlerJohn {
    DcMotor Shoulder;
    public State state;
    Constants constants = new Constants();
    public boolean targetReached = false;
    public static double angle = 0.0;

    public ShoudlerJohn(HardwareMap hardwareMap) {
        Shoulder = hardwareMap.get(DcMotor.class, "shoulder");
    }

    public void update(double looptime) {
        //use auto tuner
        int encoder = Shoulder.getCurrentPosition();

        constants.pidfController.resetConstantsAndTarget(state.params, state.target);
        targetReached = constants.pidfController.targetReached(encoder, 4.0);
        double power = constants.pidfController.calculateMotorPower(encoder, looptime);

        if (state == State.IDLE) {
            power = 0;
        }
        angle = constants.armAngle.findAngle(encoder);

        Shoulder.setPower(power);
    }

    public enum State {
        SpecimenDepositPrep(Constants.angleRanges.get(1), Constants.params.get(1)),
        SpecimenDeposit(Constants.angleRanges.get(2), Constants.params.get(2)),
        SpecimenIntake(Constants.angleRanges.get(3), Constants.params.get(3)),
        HPdrop(Constants.angleRanges.get(0), Constants.params.get(0)),
        BasketDeposit(Constants.angleRanges.get(5), Constants.params.get(5)),
        SubmersibleIntake(Constants.angleRanges.get(4), Constants.params.get(4)),
        IDLE(Constants.angleRanges.get(0), Constants.params.get(0));
        public final AngleRange target;
        public final PIDFParams params;

        State(AngleRange angleRange, PIDFParams params) {
            this.target = angleRange;
            this.params = params;
        }
    }
}

