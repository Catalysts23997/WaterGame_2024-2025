package org.firstinspires.ftc.teamcode.New.testing

import org.firstinspires.ftc.teamcode.New.Utilities.multiply1DMatrixBy2D
import org.firstinspires.ftc.teamcode.New.Utilities.multiply2DMatrixBy2D
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class MatricesTests {

    @Test
    fun test2D(){
        val matrix1 = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(4.0, 5.0, 6.0)
        )
        val matrix2 = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0),
            doubleArrayOf(5.0, 6.0)
        )

        val result = multiply2DMatrixBy2D(matrix1, matrix2)


        assertEquals(22, result[0][0].toInt())
        assertEquals(28, result[0][1].toInt())
        assertEquals(49, result[1][0].toInt())
        assertEquals(64, result[1][1].toInt())
    }
    @Test
    fun test1D(){
        val matrix1 = doubleArrayOf(1.0, 2.0, 3.0)
        val matrix2 = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0, 4.0),
            doubleArrayOf(5.0, 6.0)
        )

        val result = multiply1DMatrixBy2D(matrix1, matrix2)


        assertEquals(22, result[0].toInt())
        assertEquals(28, result[1].toInt())
    }
    @Test
    fun `test multiply1DMatrixBy2D with mismatched sizes`() {
        // Input matrices with mismatched dimensions
        val matrix1 = doubleArrayOf(1.0, 2.0, 3.0)  // Size 3
        val matrix2 = arrayOf(
            doubleArrayOf(4.0, 5.0),
            doubleArrayOf(6.0, 7.0)
        )  // Size 2x2

        // Check that the IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException::class.java) {
            multiply1DMatrixBy2D(matrix1, matrix2)
        }
    }

    @Test
    fun `test multiply1DMatrixBy2D with non-jagged matrix`() {
        // Input matrix with a jagged array (non-uniform row lengths)
        val matrix1 = doubleArrayOf(1.0, 2.0, 3.0)
        val matrix2 = arrayOf(
            doubleArrayOf(4.0, 5.0),
            doubleArrayOf(6.0, 7.0),
            doubleArrayOf(8.0)
        )  // Jagged matrix

        // Check that the IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException::class.java) {
            multiply1DMatrixBy2D(matrix1, matrix2)
        }
    }

    @Test
    fun `test multiply2DMatrixBy2D with non-jagged matrix`() {
        // Input matrix with a jagged array (non-uniform row lengths)
        val matrix1 = arrayOf(
            doubleArrayOf(1.0, 2.0),
            doubleArrayOf(3.0)  // Jagged row
        )
        val matrix2 = arrayOf(
            doubleArrayOf(4.0, 5.0),
            doubleArrayOf(6.0, 7.0)
        )

        // Check that the IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException::class.java) {
            multiply2DMatrixBy2D(matrix1, matrix2)
        }
    }

    @Test
    fun `test multiply2DMatrixBy2D with mismatched sizes`() {
        // Input matrices with mismatched dimensions
        val matrix1 = arrayOf(
            doubleArrayOf(1.0, 2.0, 3.0),
            doubleArrayOf(1.0, 2.0, 3.0) // 2x3 matrix
        )
        val matrix2 = arrayOf(
            doubleArrayOf(4.0, 5.0), // 2x2 matrix
            doubleArrayOf(6.0, 7.0)
        )

        // Check that the IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException::class.java) {
            multiply2DMatrixBy2D(matrix1, matrix2)
        }
    }
}