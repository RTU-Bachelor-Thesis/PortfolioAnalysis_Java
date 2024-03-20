package org.benchmarkings;

import org.code.PortfolioAnalysis;

import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;

@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
public class PortfolioAnalysisBenchmark {
  @Param({"10", "100", "1000"})
  private int assetsCount;
  private int periodsCount = 12;
  private double[][] profitabilityPortfolio1, profitabilityPortfolio2, weightMatrix;

  @Setup(Level.Iteration)
  public void setupPortfolio() {
    profitabilityPortfolio1 = PortfolioAnalysis.generateMatrix(assetsCount, periodsCount, -10.0, 10.0);
    profitabilityPortfolio2 = PortfolioAnalysis.generateMatrix(assetsCount, periodsCount, -10.0, 10.0);
    weightMatrix = PortfolioAnalysis.generateWeightMatrix(assetsCount, periodsCount);
  }

  @Benchmark
  public double[][] benchmarkGenerateMatrix() {
    return PortfolioAnalysis.generateMatrix(assetsCount, periodsCount, -10.0, 10.0);
  }

  @Benchmark
  public double[][] benchmarkTransposeMatrix() {
    return PortfolioAnalysis.transposeMatrix(profitabilityPortfolio1);
  }

  @Benchmark
  public double[][] benchmarkScaleMatrix() {
    return PortfolioAnalysis.scaleMatrix(profitabilityPortfolio1, Math.PI);
  }

  @Benchmark
  public double[][] benchmarkCalculateChange() {
    return PortfolioAnalysis.calculateChange(profitabilityPortfolio1);
  }

  @Benchmark
  public double[][] benchmarkSumMatrices() {
    return PortfolioAnalysis.sumMatrices(profitabilityPortfolio1, profitabilityPortfolio2);
  }

  @Benchmark
  public double[][] benchmarkMultiplyMatrices() {
    return PortfolioAnalysis.multiplyMatrices(profitabilityPortfolio1, weightMatrix);
  }

  @Benchmark
  public ArrayList<Integer> benchmarkFindIndexes() {
    return PortfolioAnalysis.findIndexes(profitabilityPortfolio1, 5, 2, 5);
  }

  @Benchmark
  public double benchmarkGetMaxSum() {
    return PortfolioAnalysis.getMaxSum(profitabilityPortfolio1);
  }
}
