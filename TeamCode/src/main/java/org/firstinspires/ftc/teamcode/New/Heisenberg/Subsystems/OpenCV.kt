package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import android.graphics.Point
import android.util.Size
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.New.Camera
import org.firstinspires.ftc.teamcode.New.Heisenberg.Globals
import org.firstinspires.ftc.teamcode.New.Utilities.Point2D
import org.firstinspires.ftc.teamcode.New.Utilities.findCameraPosition
import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor
import org.firstinspires.ftc.vision.opencv.ColorRange
import org.firstinspires.ftc.vision.opencv.ImageRegion
import java.sql.Blob

class OpenCV(hardwareMap: HardwareMap) : Camera {
    var targetRange: ColorRange
    //todo find for two colors
    init {
        targetRange = if(Globals.color == Globals.GlobalState.Red) ColorRange.RED
            else ColorRange.BLUE
    }


    override val visionProcessor: ColorBlobLocatorProcessor = ColorBlobLocatorProcessor.Builder()
        .setTargetColorRange(ColorRange.YELLOW) // use a predefined color match
        .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY) // exclude blobs inside blobs
        .setRoi(
            ImageRegion.asUnityCenterCoordinates(
                -.7,
                0.7,
                0.7,
                -0.7
            )
        ) // search central 1/4 of camera view
        .setDrawContours(true) // Show contours on the Stream Preview
        .setBlurSize(5) // Smooth the transitions between different colors in image
        .build()
    val visionProcessor2: ColorBlobLocatorProcessor = ColorBlobLocatorProcessor.Builder()
        .setTargetColorRange(targetRange) // use a predefined color match
        .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY) // exclude blobs inside blobs
        .setRoi(
            ImageRegion.asUnityCenterCoordinates(
                -.7,
                0.7,
                0.7,
                -0.7
            )
        ) // search central 1/4 of camera view
        .setDrawContours(true) // Show contours on the Stream Preview
        .setBlurSize(5) // Smooth the transitions between different colors in image
        .build()

    override val camera: WebcamName = hardwareMap.get(WebcamName::class.java, "Webcam 1")

    override val visionPortal: VisionPortal = VisionPortal.Builder()
        .addProcessors(visionProcessor,visionProcessor2)
        .setCameraResolution(Size(1920, 1080))
        .setCamera(camera)
        .setAutoStartStreamOnBuild(true)
        .build()

    data class BlobInfo(val angle: Double, val position: Point2D,val size: Double)

    fun turnOff(){
        visionPortal.stopStreaming()
    }
    fun turnOn(){
        visionPortal.resumeStreaming()
    }

    fun getBlobs(attachmentPositions: AttachmentPositions): BlobInfo? {

        val blobs1 = visionProcessor.blobs
        val blobs2  = visionProcessor2.blobs

        val blobs = blobs2 + blobs1

        /*
         * The list of Blobs can be filtered to remove unwanted Blobs.
         *
         * ColorBlobLocatorProcessor.Util.filterByArea(minArea, maxArea, blobs);
         *   A Blob's area is the number of pixels contained within the Contour.  Filter out any that are too big or small.
         *   Start with a large range and then refine the range based on the likely size of the desired object in the viewfinder.
         *
         * ColorBlobLocatorProcessor.Util.filterByDensity(minDensity, maxDensity, blobs);
         *   A blob's density is an indication of how "full" the contour is.
         *   If you put a rubber band around the contour you would get the "Convex Hull" of the contour.
         *   The density is the ratio of Contour-area to Convex Hull-area.
         *
         * ColorBlobLocatorProcessor.Util.filterByAspectRatio(minAspect, maxAspect, blobs);
         *   A blob's Aspect ratio is the ratio of boxFit long side to short side.
         *   A perfect Square has an aspect ratio of 1.  All others are > 1
         */
        ColorBlobLocatorProcessor.Util.filterByArea(
            50.0,
            20000.0,
            blobs
        )


//        val cameraPos = findCameraPosition(attachmentPositions)
        val results = ArrayList<BlobInfo>()
        blobs.forEach { blob ->

            // Display the size (area) and center location for each Blob.
            val boxFit = blob.boxFit

//
//            val d = blob.contour[boxFit.center.y.toInt(), boxFit.center.x.toInt()]
//
//            var isred = false
//            var isyellow = false
//            var isblue = false
//
//            if (d[2] > d[0]) {
//                isred = true
//                if ((d[2] + d[1]) / 2 > d[2]) {
//                    isyellow = true
//                    isred = false
//                }
//            } else {
//                isblue = true
//            }


            val position = Point2D(boxFit.center.x,boxFit.center.y)
            val angle = boxFit.angle
            val size = boxFit.size.area()
            results += BlobInfo(angle,position,size)

//            positions += findPositionOfSample(cameraPos, Point2D(cvCenter.x,cvCenter.y))
        }

        val maxBlobInfo = results.maxByOrNull { it.size }

        return maxBlobInfo
    }
}

