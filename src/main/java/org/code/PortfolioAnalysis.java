package org.code;

import static java.lang.System.*;

import java.util.ArrayList;

public class PortfolioAnalysis {
  public static void main(String[] args) {
    int assetsCount = 10;
    int periodsCount = 12;

    Portfolio originalPortfolio = new Portfolio(assetsCount, periodsCount);
    originalPortfolio.fillRandom(-10.0, 10.0);
    out.println("\nOriginal profitability portfolio (1): ");
    originalPortfolio.print();

    Portfolio transposedPortfolio = originalPortfolio.deepCopy();
    transposedPortfolio.transpose();
    out.println("\nTransposed portfolio: ");
    transposedPortfolio.print();

    Portfolio scaledPortfolio = originalPortfolio.deepCopy();
    scaledPortfolio.scale(Math.PI);
    out.println("\nScaled portfolio: ");
    scaledPortfolio.print();

    Portfolio changeMatrixPortfolio = originalPortfolio.deepCopy();
    changeMatrixPortfolio.calculateReturnChange();
    out.println("\nChange in return on assets: ");
    changeMatrixPortfolio.print();

    Portfolio additionalPortfolio = new Portfolio(assetsCount, periodsCount);
    additionalPortfolio.fillRandom(-10.0, 10.0);
    out.println("\nAdditional profitability portfolio (2): ");
    additionalPortfolio.print();

    Portfolio combinedPortfolio = originalPortfolio.deepCopy();
    combinedPortfolio.combine(additionalPortfolio);
    out.println("\nTotal profitability of portfolios: ");
    combinedPortfolio.print();

    Portfolio weightMatrix = Portfolio.createWeightsDistribution(assetsCount, periodsCount);
    out.println("\nWeight matrix: ");
    weightMatrix.print();

    Portfolio weightedPortfolio = originalPortfolio.deepCopy();
    weightedPortfolio.applyWeights(weightMatrix);
    out.println("\nWeighted portfolio: ");
    weightedPortfolio.print();

    ArrayList<Integer> indexes = originalPortfolio.findAssetsWithinReturnRange(5, 2, 5);
    out.println("\nIndexes of assets which profitability in June is greater than 2 and less than 5: ");
    out.println(indexes);

    double maxYearProfitability = originalPortfolio.findMaxTotalReturn();
    out.println("\nMaximum year profitability: ");
    out.printf("%.2f\n", maxYearProfitability);
  }
}
