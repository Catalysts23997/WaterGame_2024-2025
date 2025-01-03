package org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems

import android.util.Size
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.New.Camera
import org.firstinspires.ftc.teamcode.New.Point2D
import org.firstinspires.ftc.vision.VisionPortal
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor
import org.firstinspires.ftc.vision.opencv.ColorRange
import org.firstinspires.ftc.vision.opencv.ImageRegion

class OpenCV(hardwareMap: HardwareMap) : Camera {
    var targetColor = ColorRange.BLUE

    override val visionProcessor: ColorBlobLocatorProcessor = ColorBlobLocatorProcessor.Builder()
        .setTargetColorRange(targetColor) // use a predefined color match
        .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY) // exclude blobs inside blobs
        .setRoi(
            ImageRegion.asUnityCenterCoordinates(
                -0.5,
                0.5,
                0.5,
                -0.5
            )
        ) // search central 1/4 of camera view
        .setDrawContours(true) // Show contours on the Stream Preview
        .setBlurSize(5) // Smooth the transitions between different colors in image
        .build()

    override val camera: WebcamName = hardwareMap.get(WebcamName::class.java, "Webcam 1")

    override val visionPortal: VisionPortal = VisionPortal.Builder()
        .addProcessor(visionProcessor)
        .setCameraResolution(Size(320, 240))
        .setCamera(camera)
        .build()

    data class BlobInfo (val angle: Double, val position: Point2D)

    fun getBlobs(): List<ColorBlobLocatorProcessor.Blob> {

        val blobs = visionProcessor.blobs

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



        blobs.forEach{ blob->
//            blob.
        }

        return blobs
    }
}
