package org.test;

import org.code.Portfolio;

import static java.lang.System.*;

import org.junit.jupiter.api.*;

class PortfolioTest {
  Portfolio originalPortfolio, portfolio2, weightMatrix;

  public PortfolioTest() {
    originalPortfolio = new Portfolio(100, 12);
    originalPortfolio.fillRandom(-10.0, 10.0);

    portfolio2 = new Portfolio(100, 12);
    portfolio2.fillRandom(-10.0, 10.0);

    weightMatrix = Portfolio.createWeightsDistribution(100, 12);
  }

  @Test
  void fillRandom() {
    long startTime = System.nanoTime();
    originalPortfolio.fillRandom(-10.0, 10.0);
    long endTime = System.nanoTime();
    out.println("Time of execution of the fillRandom() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void transpose() {
    long startTime = System.nanoTime();
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.transpose();
    long endTime = System.nanoTime();
    out.println("Time of execution of the transpose() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void scale() {
    long startTime = System.nanoTime();
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.scale(Math.PI);
    long endTime = System.nanoTime();
    out.println("Time of execution of the scale() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void calculateReturnChange() {
    long startTime = System.nanoTime();
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.calculateReturnChange();
    long endTime = System.nanoTime();
    out.println("Time of execution of the calculateReturnChange() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void combine() {
    long startTime = System.nanoTime();
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.combine(portfolio2);
    long endTime = System.nanoTime();
    out.println("Time of execution of the combine() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void applyWeights() {
    long startTime = System.nanoTime();
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.applyWeights(weightMatrix);
    long endTime = System.nanoTime();
    out.println("Time of execution of the applyWeights() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void findAssetsWithinReturnRange() {
    long startTime = System.nanoTime();
    originalPortfolio.findAssetsWithinReturnRange(5, 2, 5);
    long endTime = System.nanoTime();
    out.println("Time of execution of the findAssetsWithinReturnRange() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void findMaxTotalReturn() {
    long startTime = System.nanoTime();
    originalPortfolio.findMaxTotalReturn();
    long endTime = System.nanoTime();
    out.println("Time of execution of the findMaxTotalReturn() method: " + (endTime - startTime) + " ns");
  }
}
