package org.firstinspires.ftc.teamcode.New.Competition.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.Angle;
import org.firstinspires.ftc.teamcode.New.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.PIDParams;
import org.firstinspires.ftc.teamcode.PIDTuner.Constants;

import java.util.ArrayList;

import CommonUtilities.AngleRange;
import CommonUtilities.PIDFParams;

public class ShoudlerJohn {
    DcMotor Shoulder;
    public State state;
    ArrayList<AngleRange> targets = Constants.angleRanges;
    Constants constants = new Constants();
    public boolean targetReached = false;

    public ShoudlerJohn(HardwareMap hardwareMap){
        Shoulder = hardwareMap.get(DcMotor.class,"Shoulder");
    }

    public void update(double looptime){
        //use auto tuner

        constants.pidfController.resetConstantsAndTarget(state.params, state.target);
        targetReached = constants.pidfController.targetReached(Shoulder.getCurrentPosition(), 4.0);
        double power = constants.pidfController.calculateMotorPower(Shoulder.getCurrentPosition(), looptime);

        if(state == State.IDLE){
            power = 0;
        }

        Shoulder.setPower(power);
    }

    public enum State{
        IDLE_TO_Basket(Constants.angleRanges.get(0),Constants.params.get(0)),
        IDLE_TO_Clip(Constants.angleRanges.get(0),Constants.params.get(0)),
        IDLE_TO_Submersible(Constants.angleRanges.get(0),Constants.params.get(0)),
        IDLE_TO_Ground(Constants.angleRanges.get(0),Constants.params.get(0)),
        IDLE(Constants.angleRanges.get(0),Constants.params.get(0));
        public final AngleRange target;
        public final PIDFParams params;
        State(AngleRange angleRange, PIDFParams params) {
            this.target = angleRange;
            this.params = params;
        }
    }
    //todo- implement an actual has reached boolean thing
    }

