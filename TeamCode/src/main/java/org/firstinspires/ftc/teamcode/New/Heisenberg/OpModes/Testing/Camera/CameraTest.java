package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Camera;

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
    AttachmentPositions attachmentPositions;



    @Override
    public void runOpMode() {

        OpenCV openCV = new OpenCV(hardwareMap);

        telemetry.setMsTransmissionInterval(50);   // Speed up telemetry updates, Just use for debugging.
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);

        // WARNING:  To be able to view the stream preview on the Driver Station, this code runs in INIT mode.
        while (opModeIsActive() || opModeInInit())
        {
            openCV.getVisionPortal().resumeStreaming();
            telemetry.addData("preview on/off", "... Camera Stream\n");

            List<ColorBlobLocatorProcessor.Blob> blobs = openCV.getBlobs();
            Point2D finalPoint = null;
            double max = 0;

            // Display the size (area) and center location for each Blob.
            for(ColorBlobLocatorProcessor.Blob b : blobs)
            {
                RotatedRect boxFit = b.getBoxFit();

                telemetry.addLine(String.format("%5d  %4.2f   %5.2f  (%3d,%3d)",
                        b.getContourArea(), b.getDensity(), b.getAspectRatio(), (int) boxFit.center.x, (int) boxFit.center.y));

                double[] d = b.getContour().get((int) boxFit.center.x,(int) boxFit.center.y);

                boolean isred = false;
                boolean isyellow = false;
                boolean isblue = false;

                if (d[2] > d[0]){
                    isred = true;
                    if ((d[2] + d[1])/2 > d[2]){
                        isyellow = true;
                        isred = false;
                    }
                }
                else {
                    isblue = true;
                }

                if (b.getContourArea()>max && isred){
                    max = b.getContourArea();
                    finalPoint = new Point2D(boxFit.center.x, boxFit.center.y);
                }
            }

            attachmentPositions = moveArmToPoint(10, 10, Math.PI, 10);
            Point3D cameraPos = findCameraPosition(attachmentPositions);
            Point2D PixelPos = findPositionOfSample(cameraPos, finalPoint);

            telemetry.update();
            sleep(50);
        }

    }
}
