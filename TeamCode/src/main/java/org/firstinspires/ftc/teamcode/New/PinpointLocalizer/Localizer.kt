package org.firstinspires.ftc.teamcode.New.PinpointLocalizer

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.New.Utilities.Angle
import org.firstinspires.ftc.teamcode.New.Utilities.Poses


class Localizer(hwmap: HardwareMap, private val offset: Poses) {

    private val odo: GoBildaPinpointDriver = hwmap.get(
        GoBildaPinpointDriver::class.java, "odo")
    init {
        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
        odo.setOffsets(3.5 * 25.4, -4.3 * 25.4)

        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)

        //odo.setEncoderResolution(13.26291192);


        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
         */
        odo.setEncoderDirections(
            GoBildaPinpointDriver.EncoderDirection.FORWARD,
            GoBildaPinpointDriver.EncoderDirection.FORWARD
        )

        odo.resetPosAndIMU()
    }

    var Angleoffset = 0.0
    //todo add boolean just imu or not
    fun update(justImu: Boolean = false){
        odo.update()
//        if (!justImu) odo.update() else odo.
        pose = Poses(offset.x - odo.position.getY(DistanceUnit.INCH),offset.y + odo.position.getX(DistanceUnit.INCH),
            Angle.wrap(odo.position.getHeading(AngleUnit.RADIANS) - Angleoffset +offset.heading))
    }

    fun resetHeading(){Angleoffset += pose.heading}
    companion object{
        lateinit var pose: Poses
    }
}