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

    Portfolio returnChangePortfolio = originalPortfolio.deepCopy();
    returnChangePortfolio.calculateReturnChange();
    out.println("\nChange in returns on assets: ");
    returnChangePortfolio.print();

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

    ArrayList<Integer> rangeFilteredAssets = originalPortfolio.findAssetsWithinReturnRange(5, 2, 5);
    out.println("\nIndexes of assets with returns in June greater than 2 and less than 5: " + rangeFilteredAssets);

    double maxYearProfitability = originalPortfolio.findMaxTotalReturn();
    out.printf("\nMaximum year profitability: %.2f\n", maxYearProfitability);
  }
}
