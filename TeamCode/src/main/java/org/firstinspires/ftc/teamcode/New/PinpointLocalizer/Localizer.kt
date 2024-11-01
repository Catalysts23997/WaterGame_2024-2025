package org.firstinspires.ftc.teamcode.New.PinpointLocalizer

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.Angle


class Localizer(hwmap: HardwareMap, private val offset: Pose2d) {

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
        goBildaPinpointDriver.setOffsets(-84.0, -168.0)

        goBildaPinpointDriver.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)

        goBildaPinpointDriver.setEncoderDirections(
            GoBildaPinpointDriver.EncoderDirection.FORWARD,
            GoBildaPinpointDriver.EncoderDirection.REVERSED
        )

        goBildaPinpointDriver.resetPosAndIMU()
    }

    fun update(){
        goBildaPinpointDriver.update()
        pose = Pose2d(goBildaPinpointDriver.posY+ offset.position.y,goBildaPinpointDriver.posX - offset.position.x,
            Angle.wrap(goBildaPinpointDriver.heading+offset.heading.toDouble()))
    }
    companion object{
        lateinit var pose: Pose2d
    }
}