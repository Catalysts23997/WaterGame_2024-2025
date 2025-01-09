package org.firstinspires.ftc.teamcode.New.Utilities

fun multiply1DMatrixBy2D (matrix1: DoubleArray, matrix2: Array<DoubleArray>): DoubleArray{
    val columns1 = matrix1.size
    val rows2 = matrix2.size
    val columns2 = matrix2[0].size
    val endMatrix = DoubleArray(columns2) {0.0}

    if (columns1 != rows2) {
        throw IllegalArgumentException("First matrix width must match second matrix height")
    }
    if (matrix2.any { it.size !=  columns2}) {
        throw IllegalArgumentException("2D matrix must be non-jagged")
    }

    for (i in 0 until columns2){
        for (j in 0 until columns1) {
            endMatrix[i] += matrix1[j] * matrix2[j][i]
        }
    }

    return endMatrix
}

fun multiply2DMatrixBy2D (matrix1: Array<DoubleArray>, matrix2: Array<DoubleArray>): Array<DoubleArray> {

    val rows1 = matrix1.size
    val columns1 = matrix1[0].size
    val rows2 = matrix2.size
    val columns2 = matrix2[0].size

    if (columns1 != rows2) {
        throw IllegalArgumentException("First matrix width must match second matrix height")
    }
    if (matrix1.any { it.size !=  columns1}) {
        throw IllegalArgumentException("2D matrix must be non-jagged")
    }
    if (matrix2.any { it.size !=  columns2}) {
        throw IllegalArgumentException("2D matrix must be non-jagged")
    }

    val endMatrix: Array<DoubleArray> = Array(rows1) {DoubleArray(columns2) {0.0} }


    for (i in 0 until rows1) {
        for (j in 0 until columns2) {
            for (k in 0 until columns1) {
                endMatrix[i][j] += matrix1[i][k] * matrix2[k][j]
            }
        }
    }

    return endMatrix
}
