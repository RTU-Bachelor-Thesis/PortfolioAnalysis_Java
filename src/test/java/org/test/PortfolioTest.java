package org.test;

import org.code.Portfolio;

import static java.lang.System.*;

import org.junit.jupiter.api.*;

class PortfolioTest {
  Portfolio portfolio1, portfolio2, weightMatrix;

  public PortfolioTest() {
    portfolio1 = new Portfolio(100, 12);
    portfolio1.fillRandom(-10.0, 10.0);

    portfolio2 = new Portfolio(100, 12);
    portfolio2.fillRandom(-10.0, 10.0);

    weightMatrix = Portfolio.createWeightsDistribution(100, 12);
  }

  @Test
  void fillPortfolioRandom() {
    long startTime = System.nanoTime();
    portfolio1.fillRandom(-10.0, 10.0);
    long endTime = System.nanoTime();
    out.println("Time of execution of the fillPortfolioRandom() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void transposePortfolio() {
    long startTime = System.nanoTime();
    portfolio1.transpose();
    long endTime = System.nanoTime();
    out.println("Time of execution of the transposePortfolio() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void scaleMatrix() {
    long startTime = System.nanoTime();
    portfolio1.scale(Math.PI);
    long endTime = System.nanoTime();
    out.println("Time of execution of the scale() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void calculateReturnsChange() {
    long startTime = System.nanoTime();
    portfolio1.calculateReturnsChange();
    long endTime = System.nanoTime();
    out.println("Time of execution of the calculateReturnsChange() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void combinePortfolios() {
    long startTime = System.nanoTime();
    Portfolio.combine(portfolio1, portfolio2);
    long endTime = System.nanoTime();
    out.println("Time of execution of the combinePortfolios() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void applyWeightsToPortfolio() {
    long startTime = System.nanoTime();
    portfolio1.applyWeights(weightMatrix);
    long endTime = System.nanoTime();
    out.println("Time of execution of the applyWeightsToPortfolio() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void findAssetsWithinReturnRange() {
    long startTime = System.nanoTime();
    portfolio1.findAssetsWithinReturnRange(5, 2, 5);
    long endTime = System.nanoTime();
    out.println("Time of execution of the findAssetsWithinReturnRange() method: " + (endTime - startTime) + " ns");
  }

  @Test
  void findMaxTotalReturn() {
    long startTime = System.nanoTime();
    portfolio1.findMaxTotalReturn();
    long endTime = System.nanoTime();
    out.println("Time of execution of the findMaxTotalReturn() method: " + (endTime - startTime) + " ns");
  }
}
