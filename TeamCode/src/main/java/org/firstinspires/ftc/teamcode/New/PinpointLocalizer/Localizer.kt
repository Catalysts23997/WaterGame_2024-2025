package org.firstinspires.ftc.teamcode.New.PinpointLocalizer

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D
import org.firstinspires.ftc.teamcode.New.Angle


class Localizer(hwmap: HardwareMap, private val offset: Poses) {

    private val goBildaPinpointDriver: GoBildaPinpointDriver = hwmap.get(GoBildaPinpointDriver::class.java, "odo")
    init {
        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
        goBildaPinpointDriver.setOffsets(-6.25, -6.0)

        goBildaPinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)

        goBildaPinpointDriver.setEncoderDirections(
            GoBildaPinpointDriver.EncoderDirection.FORWARD,
            GoBildaPinpointDriver.EncoderDirection.REVERSED
        )

        goBildaPinpointDriver.resetPosAndIMU()
        goBildaPinpointDriver.recalibrateIMU()
    }

    fun update(){
        goBildaPinpointDriver.update()
        pose = Poses(goBildaPinpointDriver.posY + offset.x,goBildaPinpointDriver.posX + offset.y,Angle.wrap(-goBildaPinpointDriver.heading))
    }

    fun resetHeading(){goBildaPinpointDriver.recalibrateIMU()}
    companion object{
        lateinit var pose: Poses
    }

    data class Poses (val x: Double, val y: Double, val heading: Double)
}