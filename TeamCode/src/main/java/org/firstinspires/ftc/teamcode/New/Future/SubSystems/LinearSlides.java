package org.firstinspires.ftc.teamcode.New.Future.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.apache.tools.ant.taskdefs.Tar;
import org.firstinspires.ftc.teamcode.New.PIDFcontroller;
import org.firstinspires.ftc.teamcode.New.PIDParams;

public class LinearSlides {

    public LeftSlide leftSlide;
    public RightSlide rightSlide;
    public int resetValue = 0;

    public void init(){
        leftSlide.leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public LinearSlides(HardwareMap hardwareMap){
        leftSlide = new LeftSlide(hardwareMap);
        rightSlide = new RightSlide(hardwareMap);
    }
    public enum State{
        HANG(5000),
        BASKET(430),
        CLIP(340),
        INTAKE(550),
        STATIONARY(220),
        IDLE(0),
        Manual(0);
        State(int newEncoder){
            Encoder = newEncoder;
        }
        final int Encoder;
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
    public static int manualTarget = 0;

    public void update(){
        target = state.Encoder;
        if(state == State.Manual){
            target = manualTarget;
        }
        leftSlide.update(target, state);
        rightSlide.update(target, state);
    }

    public static class LeftSlide {

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

    public static class RightSlide {
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
