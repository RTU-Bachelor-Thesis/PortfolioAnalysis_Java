package org.test;

import org.code.PortfolioAnalysis;

import static java.lang.System.*;

class PortfolioAnalysisTest {
  double[][] profitabilityPortfolio1 = PortfolioAnalysis.generateMatrix(100, 12, -10.0, 10.0);
  double[][] profitabilityPortfolio2 = PortfolioAnalysis.generateMatrix(100, 12, -10.0, 10.0);
  double[][] weightMatrix = PortfolioAnalysis.generateWeightMatrix(100, 12);

  @org.junit.jupiter.api.Test
  void generateMatrix() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.generateMatrix(100, 12, -10, 10);
    long endTime = System.nanoTime();
    out.println("Time of execution of the generateMatrix() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void transposeMatrix() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.transposeMatrix(profitabilityPortfolio1);
    long endTime = System.nanoTime();
    out.println("Time of execution of the transposeMatrix() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void scaleMatrix() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.scaleMatrix(profitabilityPortfolio1, Math.PI);
    long endTime = System.nanoTime();
    out.println("Time of execution of the scaleMatrix() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void calculateChange() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.calculateChange(profitabilityPortfolio1);
    long endTime = System.nanoTime();
    out.println("Time of execution of the calculateChange() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void sumMatrices() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.sumMatrices(profitabilityPortfolio1, profitabilityPortfolio2);
    long endTime = System.nanoTime();
    out.println("Time of execution of the sumMatrices() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void multiplyMatrices() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.multiplyMatrices(profitabilityPortfolio1, weightMatrix);
    long endTime = System.nanoTime();
    out.println("Time of execution of the multiplyMatrices() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void findIndexes() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.findIndexes(profitabilityPortfolio1, 5, 2, 5);
    long endTime = System.nanoTime();
    out.println("Time of execution of the findIndexes() method: " + (endTime - startTime) + " ns");
  }

  @org.junit.jupiter.api.Test
  void getMaxSum() {
    long startTime = System.nanoTime();
    PortfolioAnalysis.getMaxSum(profitabilityPortfolio1);
    long endTime = System.nanoTime();
    out.println("Time of execution of the getMaxSum() method: " + (endTime - startTime) + " ns");
  }
}
