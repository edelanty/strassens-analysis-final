/**
 * CPSC 450, Final Project
 * 
 * NAME: Evan Delanty
 * DATE: Fall 2024
 */ 

package cpsc450;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.io.File;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;
import static java.lang.System.out;
import java.util.Map;
import java.util.Random;


/**
 * Basic program for running performance tests and generating
 * corresponding graphs for my final project. 
 */ 
public class FinalProject {

    /*
    * Generates a random matrix given rows and columns
    */
    private static Matrix generateRandomMatrix(int rows, int cols) {
        Matrix matrix = new Matrix(rows, cols);
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.set(i, j, rand.nextDouble(101.0));
            }
        }

        return matrix;
    }

    private static long timeNaiveMultiplication(int n) {
        Matrix a = generateRandomMatrix(n, n);
        Matrix b = generateRandomMatrix(n, n);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        long start = System.currentTimeMillis();
        instance.naiveMatrixMultiplication(a, b);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long timeStrassensMultiplication(int n) {
        Matrix a = generateRandomMatrix(n, n);
        Matrix b = generateRandomMatrix(n, n);
        MatrixMultiplicationAlgorithms instance = new MatrixMultiplicationAlgorithms();
        long start = System.currentTimeMillis();
        instance.strassensMatrixMultiplication(a, b);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Create a chart
     */  
    private static void chart(XYSeries[] series, String title, String file) throws Exception {
        XYSeriesCollection ds = new XYSeriesCollection();
        for (XYSeries s : series)
            ds.addSeries(s);
        //Build the chart
        JFreeChart chart = ChartFactory.createXYLineChart(title, "Matrix Size (n x n)", "Time (ms)", ds);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(220, 220, 220));
        //Configure the chart
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        for (int i = 0; i < series.length; i++) {
            renderer.setSeriesShapesVisible(i, true);
            renderer.setSeriesShapesFilled(i, true);
            renderer.setSeriesStroke(i, new BasicStroke(2.5f));
        }
        //Save the results
        int width = 640;
        int height = 480;
        File lineChart = new File(file);
        ChartUtils.saveChartAsPNG(lineChart, chart, width, height);
    }

    static void runNaiveStressTests() throws Exception {
        out.println("----------------------------------------");
        out.println(" NAIVE MATRIX MULTIPLICATION TESTS");
        out.println("----------------------------------------");
        XYSeries timeSeries = new XYSeries("Naive");
        for (int n = 2; n <= 2048; n = n * 2) {
            long elapsedTime = timeNaiveMultiplication(n);
            timeSeries.add(n, elapsedTime);
            out.println("[Naive Multiplication] Time (ms) for " + n + "x" + n + " matrix: " + elapsedTime);
        }
        XYSeries[] series = {timeSeries};
        chart(series, "Naive Matrix Multiplication Performance", "naive_multiplication_times.png");
    }

    static void runStrassensStressTests() throws Exception {
        out.println("----------------------------------------");
        out.println(" STRASSENS MULTIPLICATION TESTS");
        out.println("----------------------------------------");
        XYSeries timeSeries = new XYSeries("Strassen");
        for (int n = 2; n <= 2048; n = n * 2) {
            long elapsedTime = timeStrassensMultiplication(n);
            timeSeries.add(n, elapsedTime);
            out.println("[Strassens Multiplication] Time (ms) for " + n + "x" + n + " matrix: " + elapsedTime);
        }
        XYSeries[] series = {timeSeries};
        chart(series, "Strassens Matrix Multiplication Performance", "strassens_multiplication_times.png");
    }

    static void runCombinedStressTests() throws Exception {
        out.println("----------------------------------------");
        out.println(" MATRIX MULTIPLICATION TESTS");
        out.println("----------------------------------------");
        XYSeries naiveSeries = new XYSeries("Naive");
        XYSeries strassenSeries = new XYSeries("Strassen");
        for (int n = 2; n <= 2048; n = n * 2) {
            long naiveElapsedTime = timeNaiveMultiplication(n);
            naiveSeries.add(n, naiveElapsedTime);
            out.println("[Naive Multiplication] Time (ms) for " + n + "x" + n + " matrix: " + naiveElapsedTime);
            //Strassens
            long strassensElapsedTime = timeStrassensMultiplication(n);
            strassenSeries.add(n, strassensElapsedTime);
            out.println("[Strassens Multiplication] Time (ms) for " + n + "x" + n + " matrix: " + strassensElapsedTime);
        }
        XYSeries[] series = {naiveSeries, strassenSeries};
        chart(series, "Matrix Multiplication Performance", "matrix_multiplication_times.png");
    }

    public static void main(String [] args) throws Exception {
        // runNaiveStressTests();
        // runStrassensStressTests();
        runCombinedStressTests();
    }

}
