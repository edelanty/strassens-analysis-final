/**
 * CPSC 450, Final Project
 * 
 * NAME: Evan Delanty
 * DATE: Fall 2024
 */ 

package cpsc450;

/**
 * Naive aproach to matrix multiplication and Strassens Algorithm
 */
public class MatrixMultiplicationAlgorithms {

    //Used as a way to modify when Strassens is utilized depending on the current size of matrices
    //Change to 1 for normal use
    static int STRASSENS_LIMIT = 1;
    
    /**
     * Naive Approach to Matrix Multiplication
     * 
     * @param a Matrix A
     * @param b Matrix B
     * @return Resulting matrix C
     */
    public Matrix naiveMatrixMultiplication(Matrix a, Matrix b) {
        //Validate that multiplication is possible
        if (a.columns() != b.rows()) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication");
        }
        
        Matrix c = new Matrix(a.rows(), b.columns());

        //Naive approach
        for (int i = 0; i < a.rows(); i++) {
            for (int j = 0; j < b.columns(); j++) {
                double sum = 0;
                
                for (int k = 0; k < a.columns(); k++) {
                    sum += a.get(i, k) * b.get(k, j);
                }

                c.set(i, j, sum);
            }
        }

        return c;
    }

    /**
     * Strassens Approach to Matrix Multiplication
     * 
     * @param a Matrix A
     * @param b Matrix B
     * @return Resulting matrix c
     */
    public Matrix strassensMatrixMultiplication(Matrix a, Matrix b) {
        //Step 1 from Cormen
        //Base case
        //If n = 1, the matrices contain a single element
        if (a.rows() <= STRASSENS_LIMIT) {
            Matrix c = new Matrix(a.rows(), b.columns());

            //****Uncomment for traditional technique**** Note only works with STRASSENS_LIMIT = 1
            // //Perform single scalar multiplication
            // c.set(0, 0, a.get(0, 0) * b.get(0, 0));

            for (int i = 0; i < a.rows(); i++) {
                for (int j = 0; j < b.columns(); j++) {
                    double sum = 0;
                    
                    for (int k = 0; k < a.columns(); k++) {
                        sum += a.get(i, k) * b.get(k, j);
                    }
    
                    c.set(i, j, sum);
                }
            }
            
            return c;
        }

        //"Otherwise, if n > 1, partition the input matrices A and B and output matrix C into n/2 x n/2 submatrices"
        int newN = a.rows() / 2;

        //Since implementation uses base 0 a11 is really a00

        //A subs
        Matrix a11 = createSubMatrix(a, 0, 0, newN);
        Matrix a12 = createSubMatrix(a, 0, newN, newN);
        Matrix a21 = createSubMatrix(a, newN, 0, newN);
        Matrix a22 = createSubMatrix(a, newN, newN, newN);

        //B subs
        Matrix b11 = createSubMatrix(b, 0, 0, newN);
        Matrix b12 = createSubMatrix(b, 0, newN, newN);
        Matrix b21 = createSubMatrix(b, newN, 0, newN);
        Matrix b22 = createSubMatrix(b, newN, newN, newN);

        //Step 2 from Cormen
        //"Create n/2 x n/2 matrices s1, s2 .. s10, each of which is the sum or difference of two submatrices from step 1"
        //*Formula's given on page 87 of Cormens*
        Matrix s1 = b12.matrixSubtraction(b22);
        Matrix s2 = a11.matrixAddition(a12);
        Matrix s3 = a21.matrixAddition(a22);
        Matrix s4 = b21.matrixSubtraction(b11);
        Matrix s5 = a11.matrixAddition(a22);
        Matrix s6 = b11.matrixAddition(b22);
        Matrix s7 = a12.matrixSubtraction(a22);
        Matrix s8 = b21.matrixAddition(b22);
        Matrix s9 = a11.matrixSubtraction(a21);
        Matrix s10 = b11.matrixAddition(b12);

        //Step 3 from Cormen
        //*Formula's given on page 87 of Cormens*
        Matrix p1 = strassensMatrixMultiplication(a11, s1);
        Matrix p2 = strassensMatrixMultiplication(s2, b22);
        Matrix p3 = strassensMatrixMultiplication(s3, b11);
        Matrix p4 = strassensMatrixMultiplication(a22, s4);
        Matrix p5 = strassensMatrixMultiplication(s5, s6);
        Matrix p6 = strassensMatrixMultiplication(s7, s8);
        Matrix p7 = strassensMatrixMultiplication(s9, s10);

        //Step 4 from Cormen
        //*Formula's given on page 88 & 89 of Cormens*
        Matrix c11 = p5.matrixAddition(p4).matrixSubtraction(p2).matrixAddition(p6);
        Matrix c12 = p1.matrixAddition(p2);
        Matrix c21 = p3.matrixAddition(p4);
        Matrix c22 = p5.matrixAddition(p1).matrixSubtraction(p3).matrixSubtraction(p7);

        //After all four steps, combining each c matrix into one resulting matrix gives the multiplication result
        
        int resultN = c11.rows();

        //Double whatever n is
        int resultSize = resultN * 2;

        Matrix c = new Matrix(resultSize, resultSize);
        
        for (int i = 0; i < resultN; i++) {
            for (int j = 0; j < resultN; j++) {
                c.set(i, j, c11.get(i, j));
                c.set(i, j + resultN, c12.get(i, j));
                c.set(i + resultN, j, c21.get(i, j));
                c.set(i + resultN, j + resultN, c22.get(i, j));
            }
        }

        return c;
    }

    /**
     * Creates a sub matrix given a new size n and starting positions row and col
     * 
     * @param m
     * @param row
     * @param col
     * @param n
     * @return A submatrix from the starting point
     */
    public Matrix createSubMatrix(Matrix m, int row, int col, int n) {
        Matrix subMatrix = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                subMatrix.set(i, j, m.get(row + i, col + j));
            }
        }

        return subMatrix;
    }

    /**
     * Validates if n x n matrix and if n of a power of 2 (requirement of Strassens), and then runs strassens
     * 
     * @param a Matrix A
     * @param b Matrix B
     * @return Resulting matrix C
     */
    public Matrix runStrassensAlgorithm(Matrix a, Matrix b) {
        //Checking for square and same size n
        if (a.rows() != a.columns() || b.rows() != b.columns() || a.rows() != b.rows()) {
            throw new IllegalArgumentException("Matrices must be square and A and B must be the same size");
        }

        //n x n matrix, n must be of a power of 2
        if ((a.rows() & (a.rows() - 1)) != 0) {
            //If n is 1 that's fine
            if (a.rows() != 1) {
                throw new IllegalArgumentException("n must be of a power of 2");
            }
        }

        return strassensMatrixMultiplication(a, b);
    }

}
