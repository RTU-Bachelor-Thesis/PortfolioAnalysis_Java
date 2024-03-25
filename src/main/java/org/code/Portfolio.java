package org.code;

import static java.lang.System.*;

import java.util.ArrayList;
import java.util.Random;

public class Portfolio {
  private double[][] data;

  public Portfolio(int rows, int columns) {
    this.data = new double[rows][columns];
  }

  public Portfolio(double[][] data) {
    this.data = data;
  }

  public Portfolio deepCopy() {
    double[][] copyData = new double[this.data.length][];
    for (int i = 0; i < this.data.length; i++) {
      copyData[i] = this.data[i].clone();
    }
    return new Portfolio(copyData);
  }

  public void fillRandom(double min, double max) {
    Random random = new Random();
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        this.data[i][j] = random.nextDouble() * (max - min) + min;
      }
    }
  }

  public void transpose() {
    double[][] transposedData = new double[this.data[0].length][this.data.length];
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        transposedData[j][i] = this.data[i][j];
      }
    }
    this.data = transposedData;
  }

  public void scale(double coefficient) {
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        this.data[i][j] *= coefficient;
      }
    }
  }

  public void calculateReturnChange() {
    double[][] changeData = new double[this.data.length][this.data[0].length - 1];
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length - 1; j++) {
        changeData[i][j] = this.data[i][j + 1] - this.data[i][j];
      }
    }
    this.data = changeData;
  }

  public void combine(Portfolio other) {
    if (this.data.length != other.data.length || this.data[0].length != other.data[0].length) {
      throw new IllegalArgumentException("Dimensions of portfolios do not match");
    }

    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        this.data[i][j] += other.data[i][j];
      }
    }
  }

  public void applyWeights(Portfolio weights) {
    for (int i = 0; i < this.data.length; i++) {
      for (int j = 0; j < this.data[i].length; j++) {
        this.data[i][j] *= weights.data[i][j];
      }
    }
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
    weights.transpose();
    return weights;
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
