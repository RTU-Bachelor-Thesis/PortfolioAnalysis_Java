package org.code;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.*;

public class PortfolioAnalysis {
  public static void main(String[] args) {
    // Matrix creation
    int assetsCount = 10;
    int periodsCount = 12;
    double[][] profitabilityPortfolio1, profitabilityPortfolio2, weightMatrix;

    profitabilityPortfolio1 = generateMatrix(assetsCount, periodsCount, -10.0, 10.0);
    out.println("\nProfitability portfolio 1");
    printMatrix(profitabilityPortfolio1);

    double[][] transposedMatrix = transposeMatrix(profitabilityPortfolio1);
    out.println("\nTransposed portfolio");
    printMatrix(transposedMatrix);

    double[][] scaledMatrix = scaleMatrix(profitabilityPortfolio1, Math.PI);
    out.println("\nScaled portfolio");
    printMatrix(scaledMatrix);

    double[][] matrixOfChange = calculateChange(profitabilityPortfolio1);
    out.println("\nChange in return on assets");
    printMatrix(matrixOfChange);

    profitabilityPortfolio2 = generateMatrix(assetsCount, periodsCount, -10.0, 10.0);
    out.println("\nProfitability portfolio 2");
    printMatrix(profitabilityPortfolio2);

    double[][] summarisedReturn = sumMatrices(profitabilityPortfolio1, profitabilityPortfolio2);
    out.println("\nTotal profitability of 2 portfolios");
    printMatrix(summarisedReturn);

    weightMatrix = generateWeightMatrix(assetsCount, periodsCount);
    out.println("\nWeight matrix");
    printMatrix(weightMatrix);

    double[][] multipliedMatrix = multiplyMatrices(profitabilityPortfolio1, weightMatrix);
    out.println("\nWeighted portfolio");
    printMatrix(multipliedMatrix);

    ArrayList<Integer> indexes = findIndexes(profitabilityPortfolio1, 5, 2, 5);
    out.println("\nIndexes of assets which profitability in June is greater than 2 and less than 5");
    out.println(indexes);

    double maxYearProfitability = getMaxSum(profitabilityPortfolio1);
    out.println("\nMaximum year profitability");
    out.printf("%.2f\n", maxYearProfitability);
  }

  // Matrix fill with randoms
  public static double[][] generateMatrix(int m, int n, double min, double max) {
    double[][] matrix = new double[m][n];
    Random random = new Random();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = random.nextDouble() * (max - min) + min;
      }
    }
    return matrix;
  }

  public static double[][] generateWeightMatrix(int m, int n) {
    double[][] matrix = new double[n][m];

    for (int j = 0; j < n; j++) {
      double sum = 0;
      for (int i = 0; i < m; i++) {
        matrix[j][i] = Math.random();
        sum += matrix[j][i];
      }
      for (int i = 0; i < m; i++) {
        matrix[j][i] /= sum;
      }
    }

    matrix = transposeMatrix(matrix);
    return matrix;
  }

  // Transpose of matrix
  public static double[][] transposeMatrix(double[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    double[][] transposed = new double[n][m];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        transposed[j][i] = matrix[i][j];
      }
    }
    return transposed;
  }

  // Matrix multiplication by a coefficient
  public static double[][] scaleMatrix(double[][] matrix, double coefficient) {
    int m = matrix.length;
    int n = matrix[0].length;
    double[][] scaledMatrix = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        scaledMatrix[i][j] = matrix[i][j] * coefficient;
      }
    }
    return scaledMatrix;
  }

  // Matrix processing by elements
  public static double[][] calculateChange(double[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    double[][] changeMatrix = new double[m][n - 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n - 1; j++) {
        changeMatrix[i][j] = matrix[i][j + 1] - matrix[i][j];
      }
    }
    return changeMatrix;
  }

  // Matrix addition
  public static double[][] sumMatrices(double[][] matrix1, double[][] matrix2) {
    int m = matrix1.length;
    int n = matrix1[0].length;
    double[][] sumMatrix = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
      }
    }
    return sumMatrix;
  }

  // Matrix multiplication
  public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
    int m = matrix1.length;
    int n = matrix1[0].length;
    double[][] multMatrix = new double[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        multMatrix[i][j] = matrix1[i][j] * matrix2[i][j];
      }
    }
    return multMatrix;
  }

  // Filtering and search
  public static ArrayList<Integer> findIndexes(double[][] matrix, int period, double min, double max) {
    int m = matrix.length;
    ArrayList<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      if (matrix[i][period] > min && matrix[i][period] < max) indexes.add(i);
    }
    return indexes;
  }

  // Reducing and aggregation
  public static double getMaxSum(double[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    double[] vector = new double[m];
    for (int i = 0; i < m; i++) {
      double sum = 0;
      for (int j = 0; j < n; j++) {
        sum += matrix[i][j];
      }
      vector[i] = sum;
    }
    double maxYearProfitability = 0f;
    for (int i = 0; i < vector.length; i++) {
      if (vector[i] > maxYearProfitability) maxYearProfitability = vector[i];
    }
    return maxYearProfitability;
  }

  // Matrix printing
  public static void printMatrix(double[][] matrix) {
    int n = matrix[0].length;
    for (double[] row : matrix) {
      for (int j = 0; j < n; j++) {
        out.printf("%8.2f", row[j]);
      }
      out.println();
    }
  }
}
