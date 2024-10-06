package org.firstinspires.ftc.teamcode.New.SubSystems.Shawty.Kotlin

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.New.PinpointLocalizer.Localizer
import org.firstinspires.ftc.teamcode.New.SubSystems.SubSystems
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

//todo Make Java version
class Drive(hwMap: HardwareMap, val aprilTagData: AprilTagData) : SubSystems {
    enum class States {
        Manual, Auto
    }

    override var state = States.Manual

    private val rightBack: DcMotor = hwMap.get(DcMotor::class.java, "rightBack")
    private val leftFront: DcMotor = hwMap.get(DcMotor::class.java, "leftFront")
    private val rightFront: DcMotor = hwMap.get(DcMotor::class.java, "rightFront")
    private val leftBack: DcMotor = hwMap.get(DcMotor::class.java, "leftBack")


    override fun update(gamepadInput: ArrayList<Float>) {
        val rx = Localizer.pose.heading.toDouble()

        when (state) {
            States.Auto -> {
                //if using april tags
                aprilTagData.state = AprilTagData.State.On
                if(aprilTagData.state == AprilTagData.State.Off) {
                    state = States.Manual
                    //run deposit sequence
                }

                //else
                aprilTagData.autoDrive.driveTo(Pose2d(-72.0, -48.0,-.75* PI))
            }

            States.Manual -> {
                val (lateral, axial, turn) = gamepadInput
                val h = -rx
                val rotX = axial * cos(h) - lateral * sin(h)
                val rotY = axial * sin(h) + lateral * cos(h)

                leftFront.power = (rotY - rotX + turn)
                leftBack.power = (rotY + rotX + turn)
                rightFront.power = (rotY + rotX - turn)
                rightBack.power = (rotY - rotX - turn)
            }
        }
    }

    init {
        leftBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightBack.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rightFront.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        leftBack.direction = DcMotorSimple.Direction.FORWARD
        leftFront.direction = DcMotorSimple.Direction.FORWARD
        rightFront.direction = DcMotorSimple.Direction.FORWARD
        rightBack.direction = DcMotorSimple.Direction.FORWARD

    }

}