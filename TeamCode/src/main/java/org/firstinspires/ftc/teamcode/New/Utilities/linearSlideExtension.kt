package org.firstinspires.ftc.teamcode.New.Utilities

import org.firstinspires.ftc.teamcode.New.Heisenberg.Subsystems.AttachmentPositions
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.sin
import kotlin.math.sqrt

fun linearSlideExtension(gD: Double): AttachmentPositions {

    val clawLength = 6.5826772
    val armLength = 6.1338583
    val slidesFromGround = 2.976378
    val minLinkageDegree = 11.3809843


    val goalDistance = gD.coerceIn(12.0, 42.0)

    val hypotenuse =
        sqrt(goalDistance * goalDistance + (clawLength - slidesFromGround) * (slidesFromGround - clawLength))

    val currentLinkageDegree =
        if (minLinkageDegree < Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))) {
            10 + Math.toDegrees(atan((clawLength - slidesFromGround) / goalDistance))
        } else {
            minLinkageDegree
        }

    val angleOppGround = Math.toDegrees(atan(goalDistance / (clawLength - slidesFromGround)))
    val angleOppArm: Double = currentLinkageDegree - (90 - angleOppGround)
    var insideArmAngle =
        Math.toDegrees(asin((hypotenuse * sin(Math.toRadians(angleOppArm))) / armLength))

    //if the arm length can possibly make two triangles because of ASS and if it is currently chosing the longer one, switch to the shortest triangle
    if ((armLength > hypotenuse * sin(Math.toRadians(angleOppArm))) && (armLength < hypotenuse) && (insideArmAngle < 90)) {
        insideArmAngle = 180 - insideArmAngle
    }
    val angleOppSlides = 180 - insideArmAngle - angleOppArm

    return AttachmentPositions(
        Math.toRadians(270 - angleOppGround - angleOppSlides),
        Math.toRadians(360 - insideArmAngle),
        (armLength * sin(Math.toRadians(angleOppSlides))) / sin(Math.toRadians(angleOppArm)),
        Math.toRadians(currentLinkageDegree)
    )
}