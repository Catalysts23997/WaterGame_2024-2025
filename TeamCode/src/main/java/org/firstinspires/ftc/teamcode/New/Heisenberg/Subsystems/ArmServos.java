package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.New.ServoPoseCalculator;

public class ArmServos {
    Servo armPitch;
    Servo clawPitch;

    public ArmState armState;

    public static double maxExtension = 37.7716535;
    public static double minExtension = maxExtension - 33;

    ServoPoseCalculator calc;


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
            armPitch.setPosition(calc.findPose(armGoal));
            clawPitch.setPosition(calc.findPose(clawGoal));
        }
        else {
            armPitch.setPosition(armState.armPos);
            clawPitch.setPosition(armState.clawPos);
        }
    }
}
