package org.firstinspires.ftc.teamcode.New.Heisenberg.OpModes.Testing.Camera;

import static org.firstinspires.ftc.teamcode.New.Utilities.ArmLengthKt.moveArmToPoint;
import static org.firstinspires.ftc.teamcode.New.Utilities.Point2DKt.findCameraPosition;
import static org.firstinspires.ftc.teamcode.New.Utilities.Point2DKt.findPositionOfSample;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions;
import org.firstinspires.ftc.teamcode.New.Utilities.CameraParams;
import org.firstinspires.ftc.teamcode.New.Utilities.Point3D;

@TeleOp(name = "CameraTest", group = "Linear OpMode")
public class CameraTest extends LinearOpMode {
    AttachmentPositions attachmentPositions;

    @Override
    public void runOpMode() {
        attachmentPositions = moveArmToPoint();
        Point3D cameraPos = findCameraPosition(attachmentPositions);
        Point3D PixelPos = findPositionOfSample(cameraPos, );
    }
}
