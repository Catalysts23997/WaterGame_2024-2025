package org.firstinspires.ftc.teamcode.New.KeepForFuture.Tele.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Disabled
@TeleOp(name = "AprilTagTest", group = "Utility")
public class AprilTagTest extends LinearOpMode {
    public static final boolean usewebcam = true;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;
    @Override
    public void runOpMode() {
        initapriltag();
        telemetry.addData("Preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                telemetryAprilTag();
                telemetry.update();
                if (gamepad1.dpad_down) {
                    visionPortal.stopStreaming();
                }
                else if (gamepad1.dpad_up) {
                    visionPortal.resumeStreaming();
                }
                sleep(20);
            }
        }
        visionPortal.close();
    }
    private void initapriltag() {
        aprilTag = new AprilTagProcessor.Builder()
                .build();
        VisionPortal.Builder builder = new VisionPortal.Builder();
        if (usewebcam) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));

        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        builder.addProcessor(aprilTag);
        visionPortal = builder.build();
    }
    private void telemetryAprilTag() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("April Tags Detected", currentDetections.size());
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");
    }
}
