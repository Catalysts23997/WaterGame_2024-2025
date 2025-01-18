import org.firstinspires.ftc.teamcode.New.Utilities.ServoPoseCalculator
import org.firstinspires.ftc.teamcode.New.Utilities.ServoRange
import org.junit.Assert.assertEquals
import org.junit.Test

class ServoPoseCalculatorTest {

    @Test
    fun `test findPose with angle within range`() {
        val servoRange = ServoRange(zeroDegrees = 0.0, rotationAngle = 180.0, rotationPosition = 1.0)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(90.0)
        assertEquals(0.5, result, 0.001) // Middle position
    }

    @Test
    fun `test findPose with angle at zero degrees`() {
        val servoRange = ServoRange(zeroDegrees = 0.0, rotationAngle = 180.0, rotationPosition = 1.0)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(0.0)
        assertEquals(0.0, result, 0.001) // Start position
    }

    @Test
    fun `test findPose with angle at rotationAngle`() {
        val servoRange = ServoRange(zeroDegrees = 0.0, rotationAngle = 180.0, rotationPosition = 1.0)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(180.0)
        assertEquals(1.0, result, 0.001) // End position
    }

    @Test
    fun `test findPose with angle below zero degrees`() {
        val servoRange = ServoRange(zeroDegrees = 0.0, rotationAngle = 180.0, rotationPosition = 1.0)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(-10.0)
        assertEquals(0.0, result, 0.001) // Clamped to start position
    }

    @Test
    fun `test findPose with angle above rotationAngle`() {
        val servoRange = ServoRange(zeroDegrees = 0.0, rotationAngle = 180.0, rotationPosition = 1.0)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(200.0)
        assertEquals(1.0, result, 0.001) // Clamped to end position
    }

    @Test
    fun `test findPose with non-zero zeroDegrees`() {
        val servoRange = ServoRange(zeroDegrees = 0.2, rotationAngle = 180.0, rotationPosition = 0.8)
        val calculator = ServoPoseCalculator(servoRange)

        val result = calculator.findPose(90.0)
        assertEquals(0.5, result, 0.001) // Correctly scaled between zeroDegrees and rotationPosition
    }
}
