//package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems;
//
//import android.util.Log;
//
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.teamcode.New.ServoPoseCalculator;
//import org.firstinspires.ftc.teamcode.New.ServoRange;
//
//public class Wrist {
//    public Servo Wrist;
//    public State state = State.Upwards;
//
//    public Wrist(HardwareMap hardwareMap) {
//        Wrist = hardwareMap.get(Servo.class, "wrist");
//        Wrist.setDirection(Servo.Direction.REVERSE);
//    }
//
//    //todo completely change the way wrist code is done - needs to be based off Inverse Kinematics
//    public enum State {
//        Upwards(0.0),
//        HpDrop(.7),
//        Basket(.34),
//        SamplePrep(0.6),
//        WallIntake(0.65),
//        Submersible(0.0);
//        public final double servoPos;
//
//        State(double servoPos) {
//            this.servoPos = servoPos;
//        }
//    }
//
//    ServoRange servoRange = new ServoRange(.94,.34);
//    ServoPoseCalculator calc = new ServoPoseCalculator(servoRange);
//
//    public void update() {
//        double targetAngle = ShoudlerJohn.angle;
//
//        if (state == State.Submersible) {
//            targetAngle += Math.PI / 2;
//        } else if(state == State.WallIntake){
//            targetAngle -= Math.PI;
//        }
//
//        if (state == State.Submersible || state == State.WallIntake || state == State.Upwards && targetAngle >0 ) {
//            Wrist.setPosition(calc.findPose(targetAngle));
//        }
//        else Wrist.setPosition(state.servoPos);
//    }
//
//}
