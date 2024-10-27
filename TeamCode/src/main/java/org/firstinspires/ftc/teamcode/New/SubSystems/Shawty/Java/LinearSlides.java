package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Java;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin.PIDParams;

public class LinearSlides {

    public LeftSlide leftSlide;
    public RightSlide rightSlide;
    public int resetValue = 0;
    int[] targets;


    public LinearSlides(HardwareMap hardwareMap){
        leftSlide = new LeftSlide(hardwareMap);
        rightSlide = new RightSlide(hardwareMap);
    }

    public void setTargets(int Hang, int Basket, int Clip, int Intake, int Stationary){
        targets[0] = Hang;
        targets[1] = Basket;
        targets[2] = Clip;
        targets[3] = Intake;
        targets[4] = Stationary;
    }

    public void updateTarget(int place, int newvalue){
        targets[place] = newvalue;
    }

    public enum State{
        HANG,
        BASKET,
        CLIP,
        INTAKE,
        STATIONARY,
        IDLE
    }

    public State state = State.IDLE;
    int target = 0;

    public boolean hasReached(){
        int leftError = target - leftSlide.leftEncoder;
        int rightError = target - rightSlide.rightEncoder;
        int averageError = (rightError + leftError)/2;
        double tolerance = 0.01;

        return averageError > -tolerance && averageError < tolerance;
    }

    public void update(){

        switch (state){
            //find height values, make code to test
            case HANG: target = targets[0]; break;
            case BASKET: target = targets[1]; break;
            case CLIP: target = targets[2]; break;
            case INTAKE: target = targets[3]; break;
            case STATIONARY: target = targets[4]; break;
        }

        leftSlide.update(target, state);
        rightSlide.update(target, state);
    }

    public class LeftSlide {

        DcMotor leftSlide;
        public int leftEncoder;

        //tune PID coefficients
        PIDParams pidParams = new PIDParams(0.0,0.0,0.0,0.0);
        PIDFcontroller pidFcontroller = new PIDFcontroller(pidParams);

        public LeftSlide(HardwareMap hardwareMap){
            leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        }


        public void update(int target, State state) {
            leftEncoder = leftSlide.getCurrentPosition();

            double leftPower = pidFcontroller.calculate(target - leftEncoder);

            //idle mode for testing or emergency stop
            if(state == State.IDLE){
               leftPower = 0;
           }

            leftSlide.setPower(leftPower);
        }
    }

    public class RightSlide {
        DcMotor rightSlide;
        public int rightEncoder;

        //tune PID coefficients
        PIDParams pidParams = new PIDParams(0.0,0.0,0.0,0.0);
        PIDFcontroller pidFcontroller = new PIDFcontroller(pidParams);

        public RightSlide(HardwareMap hardwareMap){
            rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        }

        public void update(int target, State state) {
            rightEncoder = rightSlide.getCurrentPosition();

            double rightPower = pidFcontroller.calculate(target - rightEncoder);

            //idle mode for testing or emergency stop
            if(state == State.IDLE){
                rightPower = 0;
            }

            rightSlide.setPower(rightPower);
        }
    }
}
