package orrstinspires.ftc.teamcode.New.SubSystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Teleop", group = "linear OpMode")
public class ClawOpMode extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Claws claws = new Claws(hardwareMap);



        while(opModeIsActive()){

            claws.update();

            telemetry.addData("claw", 3);
            telemetry.update();


        }


    }


}
