
package org.firstinspires.ftc.teamcode.GoBuildaComputer

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import kotlin.math.PI


class Localizer(hwmap: HardwareMap, private val offset: Poses) {

    private val odo: GoBildaPinpointDriver = hwmap.get(GoBildaPinpointDriver::class.java, "odo")
    init {
        odo.setOffsets(-6 * 25.4, 4 * 25.4)

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
    fun update(){
        odo.update()
        pose = Poses(offset.x - odo.position.getY(DistanceUnit.INCH),offset.y + odo.position.getX(DistanceUnit.INCH),
            Angle.wrap(odo.position.getHeading(AngleUnit.RADIANS) - Angleoffset +offset.heading)
        )
    }

    fun resetHeading(){Angleoffset += pose.heading}
    companion object{
        lateinit var pose: Poses
    }

    data class Poses (val x: Double, val y: Double, val heading: Double)

    object Angle {
        fun wrap(theta: Double): Double {
            var angle = theta
            while (angle > PI) angle -= PI * 2
            while (angle < -PI) angle += PI * 2
            return angle
        }
        fun wrapToPositive(theta: Double): Double {
            require(theta in -2 * PI..2 * PI)
            var angle = theta
            angle = wrap(angle)
            while (angle> PI) angle -= PI
            while (angle<0) angle += PI
            return angle
        }
    }


}