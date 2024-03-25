package org.benchmarkings;

import org.code.Portfolio;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@Fork(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class PortfolioBenchmark {
  @Param({"10", "100", "1000"})
  private int assetsCount;
  private int periodsCount = 12;
  private Portfolio originalPortfolio, portfolio2, weightMatrix;

  @Setup(Level.Iteration)
  public void setupPortfolio() {
    originalPortfolio = new Portfolio(assetsCount, periodsCount);
    originalPortfolio.fillRandom(-10.0, 10.0);

    portfolio2 = new Portfolio(assetsCount, periodsCount);
    portfolio2.fillRandom(-10.0, 10.0);

    weightMatrix = Portfolio.createWeightsDistribution(assetsCount, periodsCount);
  }

  @Benchmark
  public void benchmarkFillRandom() {
    originalPortfolio.fillRandom(-10.0, 10.0);
  }

  @Benchmark
  public void benchmarkTranspose() {
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.transpose();
  }

  @Benchmark
  public void benchmarkScale() {
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.scale(Math.PI);
  }

  @Benchmark
  public void benchmarkCalculateReturnChange() {
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.calculateReturnChange();
  }

  @Benchmark
  public void benchmarkCombine() {
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.combine(portfolio2);
  }

  @Benchmark
  public void benchmarkApplyWeights() {
    Portfolio originalPortfolioCopy = originalPortfolio.deepCopy();
    originalPortfolioCopy.applyWeights(weightMatrix);
  }

  @Benchmark
  public ArrayList<Integer> benchmarkFindAssetsWithinReturnRange() {
    return originalPortfolio.findAssetsWithinReturnRange(5, 2, 5);
  }

  @Benchmark
  public double benchmarkFindMaxTotalReturn() {
    return originalPortfolio.findMaxTotalReturn();
  }
}
