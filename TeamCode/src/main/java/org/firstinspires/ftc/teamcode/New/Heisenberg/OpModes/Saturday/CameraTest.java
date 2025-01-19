package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Globals;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.ClawRotater;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.OpenCV;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.Wrists;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@TeleOp(name = "CameraTest", group = "Linear OpMode")
public class CameraTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry = FtcDashboard.getInstance().getTelemetry();

        Globals.color = Globals.GlobalState.Red;
        OpenCV openCV = new OpenCV(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        ClawRotater clawRotater = new ClawRotater(hardwareMap);
        Wrists wrists = new Wrists(hardwareMap);


        waitForStart();
        while (opModeIsActive()) {
            openCV.getVisionPortal().resumeStreaming();
            OpenCV.BlobInfo blobInfo= openCV.getBlobs();

            if(blobInfo!=null) {
                telemetry.addData("Position", blobInfo.getPosition().toString());
                telemetry.addData("Angle", blobInfo.getAngle());
            }

            claw.clawState = Claw.ClawState.OPEN;
            wrists.state = Wrists.State.Basket;
            clawRotater.update(0.0);
            claw.update();
            wrists.update();
            telemetry.update();
            clawRotater.update(0.0);
        }
    }
}
