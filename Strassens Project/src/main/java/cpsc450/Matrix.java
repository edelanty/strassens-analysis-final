/**
 * CPSC 450, Final Project
 * 
 * NAME: Evan Delanty
 * DATE: Fall 2024
 */

package cpsc450;

public class Matrix {
    private int rows;
    private int cols;
    private double[] flattenedMatrix;

    /**
     * EVC to create a matrix with given rows and columns
     *
     * @param rows Number of rows in the matrix
     * @param cols Number of columns in the matrix
     */
    public Matrix (int rows, int cols) {
        //Ensures no negative or empty rows or columns
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Negative row or column value entered");
        }

        this.rows = rows;
        this.cols = cols;
        this.flattenedMatrix = new double[rows * cols];
    }

    /**
     * Gets the value at the specified row and column
     *
     * @param row Row index
     * @param col Column index
     * @return The value at the specified row and column in the matrix
     */
    public double get(int row, int col) {
        int flattenedIndex = row * cols + col;

        checkIndices(row, col);

        return flattenedMatrix[flattenedIndex];
    }

    /**
     * Sets the value at the specified row and column
     *
     * @param row Row index
     * @param col Column index
     * @param data The value to set
     */
    public void set(int row, int col, double data) {
        int flattenedIndex = row * cols + col;

        checkIndices(row, col);

        flattenedMatrix[flattenedIndex] = data;
    }

    /**
     * Returns the number of rows in the matrix
     *
     * @return Number of rows
     */
    public int rows() {
        return rows;
    }

    /**
     * Returns the number of columns in the matrix
     *
     * @return Number of columns
     */
    public int columns() {
        return cols;
    }

    /**
     * Validates that the given row and column indices are within matrix bounds
     *
     * @param row Row index
     * @param col Column index
     */
    private void checkIndices(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Entered row or column not in bounds of Matrix");
        }
    }

    /**
     * Adds two matrices and returns the result 
     * 
     * @param m Matrix to add
     * @return
     */
    public Matrix matrixAddition(Matrix m) {
        //Additional check to make sure addition is possible
        if (this.rows != m.rows || this.cols != m.cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match for addition");
        }

        Matrix sumMatrix = new Matrix(this.rows, this.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix.set(i, j, this.get(i, j) + m.get(i, j));
            }
        }

        return sumMatrix;
    }

    /**
     * Subtracts two matrices and returns the result
     * 
     * @param m Matrix to subtract
     * @return
     */
    public Matrix matrixSubtraction(Matrix m) {
        //Additional check to make sure subtraction is possible
        if (this.rows != m.rows || this.cols != m.cols) {
            throw new IllegalArgumentException("Matrix dimensions do not match for subtraction");
        }

        Matrix differenceMatrix = new Matrix(this.rows, this.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                differenceMatrix.set(i, j, this.get(i, j) - m.get(i, j));
            }
        }

        return differenceMatrix;
    }

    /**
     * Allows for comparison during unit testing
     * 
     * @param obj Matix
     * @return True or false if equal matrices
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Matrix other = (Matrix) obj;

        //Check dimensions
        if (rows != other.rows || cols != other.cols) {
            return false;
        }

        //Check matrix contents
        return java.util.Arrays.equals(flattenedMatrix, other.flattenedMatrix);
    }

}
