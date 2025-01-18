package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Saturday;

import static org.firstinspires.ftc.teamcode.New.Utilities.ArmLengthKt.moveArmToPoint;
import static org.firstinspires.ftc.teamcode.New.Utilities.Point2DKt.findCameraPosition;
import static org.firstinspires.ftc.teamcode.New.Utilities.Point2DKt.findPositionOfSample;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.OpenCV;
import org.firstinspires.ftc.teamcode.New.Utilities.CameraParams;
import org.firstinspires.ftc.teamcode.New.Utilities.Point2D;
import org.firstinspires.ftc.teamcode.New.Utilities.Point2DKt;
import org.firstinspires.ftc.teamcode.New.Utilities.Point3D;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "CameraTest", group = "Linear OpMode")
public class CameraTest extends LinearOpMode {


    @Override
    public void runOpMode() {

        OpenCV openCV = new OpenCV(hardwareMap);

        // WARNING:  To be able to view the stream preview on the Driver Station, this code runs in INIT mode.
        while (opModeIsActive() || opModeInInit())
        {
            openCV.getVisionPortal().resumeLiveView();
            openCV.getVisionPortal().resumeStreaming();
            OpenCV.BlobInfo blobInfo= openCV.getBlobs(new AttachmentPositions(0.0,0.0,0.0,0.0));

            if(blobInfo!=null) {
                telemetry.addData("Position", blobInfo.getPosition().toString());
                telemetry.addData("Angle", blobInfo.getAngle());
            }

            telemetry.update();
            sleep(30);
        }

    }
}
