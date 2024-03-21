package org.benchmarkings;

import org.code.Portfolio;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;

@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
public class PortfolioBenchmark {
  @Param({"10", "100", "1000"})
  private int assetsCount;
  private int periodsCount = 12;
  private Portfolio portfolio1, portfolio2, weightMatrix;

  @Setup(Level.Iteration)
  public void setupPortfolio() {
    portfolio1 = new Portfolio(assetsCount, periodsCount);
    portfolio1.fillRandom(-10.0, 10.0);

    portfolio2 = new Portfolio(assetsCount, periodsCount);
    portfolio2.fillRandom(-10.0, 10.0);

    weightMatrix = Portfolio.createWeightsDistribution(assetsCount, periodsCount);
  }

  @Benchmark
  public void benchmarkFillRandom() {
    portfolio1.fillRandom(-10.0, 10.0);
  }

  @Benchmark
  public Portfolio benchmarkTranspose() {
    return portfolio1.transpose();
  }

  @Benchmark
  public Portfolio benchmarkScale() {
    return portfolio1.scale(Math.PI);
  }

  @Benchmark
  public Portfolio benchmarkCalculateReturnsChange() {
    return portfolio1.calculateReturnsChange();
  }

  @Benchmark
  public Portfolio benchmarkCombine() {
    return Portfolio.combine(portfolio1, portfolio2);
  }

  @Benchmark
  public Portfolio benchmarkApplyWeights() {
    return portfolio1.applyWeights(weightMatrix);
  }

  @Benchmark
  public ArrayList<Integer> benchmarkFindAssetsWithinReturnRange() {
    return portfolio1.findAssetsWithinReturnRange(5, 2, 5);
  }

  @Benchmark
  public double benchmarkFindMaxTotalReturn() {
    return portfolio1.findMaxTotalReturn();
  }
}
