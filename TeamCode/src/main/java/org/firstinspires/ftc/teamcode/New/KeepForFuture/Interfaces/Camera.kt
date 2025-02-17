package org.firstinspires.ftc.teamcode.New.KeepForFuture.Interfaces

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.VisionProcessor

interface Camera {

    val camera: WebcamName
    val visionPortal : VisionPortal
    val visionProcessor: VisionProcessor

}