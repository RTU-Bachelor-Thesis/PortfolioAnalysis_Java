package org.code;

import static java.lang.System.*;

import java.util.ArrayList;

public class PortfolioAnalysis {
  public static void main(String[] args) {
    int assetsCount = 10;
    int periodsCount = 12;

    Portfolio portfolio1 = new Portfolio(assetsCount, periodsCount);
    portfolio1.fillRandom(-10.0, 10.0);
    out.println("\nProfitability portfolio 1");
    portfolio1.print();

    Portfolio transposed = portfolio1.transpose();
    out.println("\nTransposed portfolio");
    transposed.print();

    Portfolio scaled = portfolio1.scale(Math.PI);
    out.println("\nScaled portfolio");
    scaled.print();

    Portfolio matrixOfChange = portfolio1.calculateReturnsChange();
    out.println("\nChange in return on assets");
    matrixOfChange.print();

    Portfolio portfolio2 = new Portfolio(assetsCount, periodsCount);
    portfolio2.fillRandom(-10.0, 10.0);
    out.println("\nProfitability portfolio 2");
    portfolio2.print();

    Portfolio summarisedReturn = Portfolio.combine(portfolio1, portfolio2);
    out.println("\nTotal profitability of 2 portfolios");
    summarisedReturn.print();

    Portfolio weightMatrix = Portfolio.createWeightsDistribution(assetsCount, periodsCount);
    out.println("\nWeight matrix");
    weightMatrix.print();

    Portfolio multipliedMatrix = portfolio1.applyWeights(weightMatrix);
    out.println("\nWeighted portfolio");
    multipliedMatrix.print();

    ArrayList<Integer> indexes = portfolio1.findAssetsWithinReturnRange(5, 2, 5);
    out.println("\nIndexes of assets which profitability in June is greater than 2 and less than 5");
    out.println(indexes);

    double maxYearProfitability = portfolio1.findMaxTotalReturn();
    out.println("\nMaximum year profitability");
    out.printf("%.2f\n", maxYearProfitability);
  }
}
