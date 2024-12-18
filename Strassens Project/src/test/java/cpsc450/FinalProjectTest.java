/**
 * CPSC 450, Final Project
 * 
 * NAME: Evan Delanty
 * DATE: Fall 2024
 *
 * Unit tests for naive matrix multiplication and strassens algorithm.
 */

package cpsc450;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class FinalProjectTest {

    //======================================================================
    // Naive Multiplication Tests
    //======================================================================

    @Test
    void naiveApproachInvalidInputTest() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(3, 4);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.naiveMatrixMultiplication(a, b);
        });
    }

    @Test
    void naiveApproachTwoByOneXOneByTwoTest() {
        Matrix a = new Matrix(2, 1);
        Matrix b = new Matrix(1, 2);
        Matrix c = new Matrix(2, 2);
        //Setting a data
        a.set(0, 0, 1);
        a.set(1, 0, 2);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 1);
        //Setting resulting matrix c
        c.set(0, 0, 2);
        c.set(0, 1, 1);
        c.set(1, 0, 4);
        c.set(1, 1, 2);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.naiveMatrixMultiplication(a, b));
    }

    @Test
    void naiveApproachTwoByTwoXTwoByTwoTest() {
        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);
        Matrix c = new Matrix(2, 2);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(1, 0, 3);
        a.set(1, 1, 4);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(1, 0, 1);
        b.set(1, 1, 2);
        //Setting resulting matrix c
        c.set(0, 0, 4);
        c.set(0, 1, 4);
        c.set(1, 0, 10);
        c.set(1, 1, 8);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.naiveMatrixMultiplication(a, b));
    }

    @Test
    void naiveApproachFourByFourXFourByFourTest() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(4, 4);
        Matrix c = new Matrix(4, 4);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(0, 2, 3);
        a.set(0, 3, 4);
        a.set(1, 0, 5);
        a.set(1, 1, 6);
        a.set(1, 2, 7);
        a.set(1, 3, 8);
        a.set(2, 0, 9);
        a.set(2, 1, 10);
        a.set(2, 2, 11);
        a.set(2, 3, 12);
        a.set(3, 0, 13);
        a.set(3, 1, 14);
        a.set(3, 2, 15);
        a.set(3, 3, 16);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(0, 2, 1);
        b.set(0, 3, 2);
        b.set(1, 0, 3);
        b.set(1, 1, 4);
        b.set(1, 2, 5);
        b.set(1, 3, 6);
        b.set(2, 0, 7);
        b.set(2, 1, 8);
        b.set(2, 2, 9);
        b.set(2, 3, 10);
        b.set(3, 0, 11);
        b.set(3, 1, 12);
        b.set(3, 2, 13);
        b.set(3, 3, 14);
        //Setting resulting matrix c
        c.set(0, 0, 73);
        c.set(0, 1, 80);
        c.set(0, 2, 90);
        c.set(0, 3, 100);
        c.set(1, 0, 165);
        c.set(1, 1, 176);
        c.set(1, 2, 202);
        c.set(1, 3, 228);
        c.set(2, 0, 257);
        c.set(2, 1, 272);
        c.set(2, 2, 314);
        c.set(2, 3, 356);
        c.set(3, 0, 349);
        c.set(3, 1, 368);
        c.set(3, 2, 426);
        c.set(3, 3, 484);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.naiveMatrixMultiplication(a, b));
    }

    @Test
    void naiveMatrixXIdentityMatrixTest() {
        Matrix a = new Matrix(8, 8);
        Matrix b = new Matrix(8, 8);
        //Setting a data
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                a.set(i, j, i + j);
            }
        }
        //Setting b data (identity matrix)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == j) {
                    b.set(i, j, 1);
                } else {
                    b.set(i, j, 0);
                }
            }
        }
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        //a x I should return a
        assertEquals(a, instance.naiveMatrixMultiplication(a, b));
    }

    //======================================================================
    // Matrix Addition Tests
    //======================================================================

    @Test
    void matrixAdditionInvalidInputTest() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(3, 4);
        assertThrows(IllegalArgumentException.class, () -> {
            a.matrixAddition(b);
        });
    }

    @Test
    void matrixAdditionTwoByTwoPlusTwoByTwoTest() {
        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);
        Matrix c = new Matrix(2, 2);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(1, 0, 3);
        a.set(1, 1, 4);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(1, 0, 1);
        b.set(1, 1, 2);
        //Setting resulting matrix c
        c.set(0, 0, 3);
        c.set(0, 1, 2);
        c.set(1, 0, 4);
        c.set(1, 1, 6);
        assertEquals(c, a.matrixAddition(b));
    }

    //======================================================================
    // Matrix Subtraction Tests
    //======================================================================

    @Test
    void matrixSubtractionInvalidInputTest() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(3, 4);
        assertThrows(IllegalArgumentException.class, () -> {
            a.matrixSubtraction(b);
        });
    }

    @Test
    void matrixSubtractionTwoByTwoPlusTwoByTwoTest() {
        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);
        Matrix c = new Matrix(2, 2);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(1, 0, 3);
        a.set(1, 1, 4);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(1, 0, 1);
        b.set(1, 1, 2);
        //Setting resulting matrix c
        c.set(0, 0, -1);
        c.set(0, 1, 2);
        c.set(1, 0, 2);
        c.set(1, 1, 2);
        assertEquals(c, a.matrixSubtraction(b));
    }

    //======================================================================
    // Create Submatrices Tests
    //======================================================================

    @Test
    void createSubMatrixCorrectnessTest() {
        Matrix a = new Matrix(4, 4);
        Matrix c = new Matrix(2, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a.set(i, j, i + j);
            }
        }
        c.set(0, 0, 2);
        c.set(0, 1, 3);
        c.set(1, 0, 3);
        c.set(1, 1, 4);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.createSubMatrix(a, 1, 1, 2));
    }

    //======================================================================
    // Strassens Multiplication Tests
    //======================================================================

    @Test
    void strassensApproachNotSquareTest() {
        Matrix a = new Matrix(4, 3);
        Matrix b = new Matrix(3, 4);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.runStrassensAlgorithm(a, b);
        });
    }

    @Test
    void strassensApproachSquareNotSameN() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(3, 3);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.runStrassensAlgorithm(a, b);
        });
    }

    @Test
    void strassensApproachNotOfPowerOfTwoTest() {
        Matrix a = new Matrix(3, 3);
        Matrix b = new Matrix(3, 3);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertThrows(IllegalArgumentException.class, () -> {
            instance.runStrassensAlgorithm(a, b);
        });
    }

    @Test
    void strassensMinumumSizeOneByOneXOneByOneTest() {
        Matrix a = new Matrix(1, 1);
        Matrix b = new Matrix(1, 1);
        Matrix c = new Matrix(1, 1);
        //Setting a data
        a.set(0, 0, 2);
        //Setting b data
        b.set(0, 0, 2);
        //Setting resulting matrix c
        c.set(0, 0, 4);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.runStrassensAlgorithm(a, b));
    }

    @Test
    void strassensApproachTwoByTwoXTwoByTwoTest() {
        Matrix a = new Matrix(2, 2);
        Matrix b = new Matrix(2, 2);
        Matrix c = new Matrix(2, 2);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(1, 0, 3);
        a.set(1, 1, 4);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(1, 0, 1);
        b.set(1, 1, 2);
        //Setting resulting matrix c
        c.set(0, 0, 4);
        c.set(0, 1, 4);
        c.set(1, 0, 10);
        c.set(1, 1, 8);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.runStrassensAlgorithm(a, b));
    }

    @Test
    void strassensApproachFourByFourXFourByFourTest() {
        Matrix a = new Matrix(4, 4);
        Matrix b = new Matrix(4, 4);
        Matrix c = new Matrix(4, 4);
        //Setting a data
        a.set(0, 0, 1);
        a.set(0, 1, 2);
        a.set(0, 2, 3);
        a.set(0, 3, 4);
        a.set(1, 0, 5);
        a.set(1, 1, 6);
        a.set(1, 2, 7);
        a.set(1, 3, 8);
        a.set(2, 0, 9);
        a.set(2, 1, 10);
        a.set(2, 2, 11);
        a.set(2, 3, 12);
        a.set(3, 0, 13);
        a.set(3, 1, 14);
        a.set(3, 2, 15);
        a.set(3, 3, 16);
        //Setting b data
        b.set(0, 0, 2);
        b.set(0, 1, 0);
        b.set(0, 2, 1);
        b.set(0, 3, 2);
        b.set(1, 0, 3);
        b.set(1, 1, 4);
        b.set(1, 2, 5);
        b.set(1, 3, 6);
        b.set(2, 0, 7);
        b.set(2, 1, 8);
        b.set(2, 2, 9);
        b.set(2, 3, 10);
        b.set(3, 0, 11);
        b.set(3, 1, 12);
        b.set(3, 2, 13);
        b.set(3, 3, 14);
        //Setting resulting matrix c
        c.set(0, 0, 73);
        c.set(0, 1, 80);
        c.set(0, 2, 90);
        c.set(0, 3, 100);
        c.set(1, 0, 165);
        c.set(1, 1, 176);
        c.set(1, 2, 202);
        c.set(1, 3, 228);
        c.set(2, 0, 257);
        c.set(2, 1, 272);
        c.set(2, 2, 314);
        c.set(2, 3, 356);
        c.set(3, 0, 349);
        c.set(3, 1, 368);
        c.set(3, 2, 426);
        c.set(3, 3, 484);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        assertEquals(c, instance.runStrassensAlgorithm(a, b));
    }

    @Test
    void strassensMatrixXIdentityMatrixTest() {
        Matrix a = new Matrix(8, 8);
        Matrix b = new Matrix(8, 8);
        //Setting a data
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                a.set(i, j, i + j);
            }
        }
        //Setting b data (identity matrix)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == j) {
                    b.set(i, j, 1);
                } else {
                    b.set(i, j, 0);
                }
            }
        }
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        //a x I should return a
        assertEquals(a, instance.runStrassensAlgorithm(a, b));
    }

    //Note any larger makes the test runner run for a bit too long...
    @Test
    void strassensApproachLargeMatrix512x512Test() {
        Matrix a = new Matrix(512, 512);
        Matrix b = new Matrix(512, 512);
        Matrix c = new Matrix(512, 512);
        //Setting a data
        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                a.set(i, j, i + j);
            }
        }
        //Setting b data
        for (int i = 0; i < 512; i++) {
            for (int j = 0; j < 512; j++) {
                b.set(i, j, i + j);
            }
        }
        //Use naive approach to get c data
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        c = instance.naiveMatrixMultiplication(a, b);
        assertEquals(c, instance.runStrassensAlgorithm(a, b));
    }

}
