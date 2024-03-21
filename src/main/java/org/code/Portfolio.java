package org.code;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Random;

public class Portfolio {
  private double[][] data;

  public Portfolio(int rows, int columns) {
    data = new double[rows][columns];
  }

  public void fillRandom(double min, double max) {
    Random random = new Random();
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        data[i][j] = random.nextDouble() * (max - min) + min;
      }
    }
  }

  public Portfolio transpose() {
    Portfolio result = new Portfolio(data[0].length, data.length);
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        result.data[j][i] = this.data[i][j];
      }
    }
    return result;
  }

  public Portfolio scale(double coefficient) {
    Portfolio result = new Portfolio(data.length, data[0].length);
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        result.data[i][j] = this.data[i][j] * coefficient;
      }
    }
    return result;
  }

  public Portfolio calculateReturnsChange() {
    Portfolio change = new Portfolio(data.length, data[0].length - 1);
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length - 1; j++) {
        change.data[i][j] = data[i][j + 1] - data[i][j];
      }
    }
    return change;
  }

  public static Portfolio combine(Portfolio portfolio1, Portfolio portfolio2) {
    Portfolio sum = new Portfolio(portfolio1.data.length, portfolio1.data[0].length);
    for (int i = 0; i < sum.data.length; i++) {
      for (int j = 0; j < sum.data[i].length; j++) {
        sum.data[i][j] = portfolio1.data[i][j] + portfolio2.data[i][j];
      }
    }
    return sum;
  }

  public static Portfolio createWeightsDistribution(int assets, int periods) {
    Portfolio weights = new Portfolio(periods, assets);
    Random random = new Random();
    for (int j = 0; j < periods; j++) {
      double sum = 0;
      for (int i = 0; i < assets; i++) {
        weights.data[j][i] = random.nextDouble();
        sum += weights.data[j][i];
      }
      for (int i = 0; i < assets; i++) {
        weights.data[j][i] /= sum;
      }
    }
    return weights.transpose();
  }

  public Portfolio applyWeights(Portfolio weights) {
    Portfolio result = new Portfolio(data.length, data[0].length);
    for (int i = 0; i < result.data.length; i++) {
      for (int j = 0; j < result.data[i].length; j++) {
        result.data[i][j] = data[i][j] * weights.data[i][j];
      }
    }
    return result;
  }

  public ArrayList<Integer> findAssetsWithinReturnRange(int period, double min, double max) {
    ArrayList<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < data.length; i++) {
      if (data[i][period] > min && data[i][period] < max) {
        indexes.add(i);
      }
    }
    return indexes;
  }

  public double findMaxTotalReturn() {
    double maxSum = Double.MIN_VALUE;
    for (double[] asset : data) {
      double sum = 0;
      for (double period : asset) {
        sum += period;
      }
      if (sum > maxSum) {
        maxSum = sum;
      }
    }
    return maxSum;
  }

  public void print() {
    for (double[] row : data) {
      for (double element : row) {
        out.printf("%8.2f", element);
      }
      out.println();
    }
  }
}
