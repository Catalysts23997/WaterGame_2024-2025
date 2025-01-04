package org.firstinspires.ftc.teamcode.New.Utilities

fun multiply1DMatrixBy2D (matrix1: DoubleArray, matrix2: Array<DoubleArray>): DoubleArray{

    if (matrix1.size != matrix2[0].size) {
        throw IllegalArgumentException("First matrix width must match second matrix height")
    }
    if (matrix2.any { it.size !=  matrix2[0].size}) {
        throw IllegalArgumentException("2D matrix must be non-jagged")
    }

    val row1 = matrix1.size
    val row2 = matrix2.size
    val endMatrix = DoubleArray(row2) {0.0}


    for (i in 0 until row2){
        for (j in 0 until row1) {
            endMatrix[i] += matrix1[j] * matrix2[i][j]
        }
    }

    return endMatrix
}