package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator;
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange;

public class ArmServos {
    Servo armPitch;
    Servo clawPitch;
    public ArmState armState;
    public static double maxExtension = 37.7716535;
    public static double minExtension = maxExtension - 33;
    ServoPoseCalculator armCalc  = new ServoPoseCalculator(new ServoRange(0.0,Math.PI,1.0));
    ServoPoseCalculator clawCalc  = new ServoPoseCalculator(new ServoRange(0.0,Math.PI,1.0));


    public ArmServos(HardwareMap hardwareMap){
        //todo
    }

    public enum ArmState {
        STATIONARY(0.9, 0.0),
        INPUT(0.0,0.0);

        public final double armPos;
        public final double clawPos;

        ArmState(double armPos, double clawPos) {
            this.armPos = armPos;
            this.clawPos = clawPos;
        }
    }

    public void update(double armGoal, double clawGoal){
        if (armState == ArmState.INPUT){
            armPitch.setPosition(armCalc.findPose(armGoal));
            clawPitch.setPosition(clawCalc.findPose(clawGoal));
        }
        else {
            armPitch.setPosition(armState.armPos);
            clawPitch.setPosition(armState.clawPos);
        }
    }
}
