package exercise7;

import java.util.HashMap;
import java.util.Map;

/**
 * ForecastingTest is a driver program that tests and analyzes
 * the performance, correctness, and limits of recursion vs. optimization.
 */
public class ForecastingTest {
    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool Test ===\n");

        double pv = 1000.00;
        double rate = 0.05; // 5% growth
        int periods = 10;

        System.out.println("--- Test 1: Simple Recursive Forecasting (Constant Rate) ---");
        System.out.println("Present Value: $" + pv);
        System.out.println("Constant Growth Rate: " + (rate * 100) + "%");
        System.out.println("Periods (Years): " + periods);
        double fvRecursive = FinancialForecasting.calculateFutureValue(pv, rate, periods);
        System.out.printf("Predicted Future Value: $%.2f (Expected: $1628.89)%n%n", fvRecursive);

        System.out.println("--- Test 2: Recursive Forecasting with Varying Growth Rates ---");
        double[] pastRates = {0.04, 0.06, -0.02, 0.05, 0.07}; // 5 years of varying growth
        System.out.println("Present Value: $" + pv);
        System.out.print("Varying Growth Rates: [");
        for (int i = 0; i < pastRates.length; i++) {
            System.out.print((pastRates[i] * 100) + "%" + (i < pastRates.length - 1 ? ", " : ""));
        }
        System.out.println("]");
        double fvVarying = FinancialForecasting.calculateFutureValueVarying(pv, pastRates, 0);
        System.out.printf("Predicted Future Value after %d years: $%.2f%n%n", pastRates.length, fvVarying);

        System.out.println("--- Test 3: Average Growth Rate Projection ---");
        double avgRate = FinancialForecasting.calculateAverageRate(pastRates);
        System.out.printf("Average Past Growth Rate: %.2f%%%n", (avgRate * 100));
        double fvFromAvg = FinancialForecasting.calculateFutureValue(pv, avgRate, periods);
        System.out.printf("Projected Future Value after %d years based on average rate: $%.2f%n%n", periods, fvFromAvg);

        System.out.println("--- Test 4: Optimized Recursive Forecasting (Memoized) ---");
        Map<Integer, Double> memo = new HashMap<>();
        double fvMemoized = FinancialForecasting.calculateFutureValueMemoized(pv, rate, periods, memo);
        System.out.printf("Memoized Future Value: $%.2f%n", fvMemoized);
        System.out.println("Memo cache size: " + memo.size());
        System.out.println("Memo cache contents (period -> value):");
        memo.forEach((k, v) -> System.out.printf("  Period %2d: $%.2f%n", k, v));
        System.out.println();

        System.out.println("--- Test 5: Optimized Iterative Forecasting ---");
        double fvIterative = FinancialForecasting.calculateFutureValueIterative(pv, rate, periods);
        System.out.printf("Iterative Future Value: $%.2f%n%n", fvIterative);

        System.out.println("--- Test 6: Call Stack Safety Demonstration ---");
        int largePeriods = 25000;
        System.out.println("Attempting recursive forecast with " + largePeriods + " periods...");
        try {
            double fvLargeRec = FinancialForecasting.calculateFutureValue(pv, rate, largePeriods);
            System.out.printf("Success (Recursive): $%.2f%n", fvLargeRec);
        } catch (StackOverflowError e) {
            System.out.println("CRASH DETECTED: Java Call Stack Overflow! Recursion exceeded maximum depth.");
        }

        System.out.println("\nAttempting iterative forecast with " + largePeriods + " periods...");
        try {
            double fvLargeIter = FinancialForecasting.calculateFutureValueIterative(pv, rate, largePeriods);
            System.out.printf("SUCCESS! Future Value (Iterative): $%.4e%n", fvLargeIter);
            System.out.println("Reason: Iterative approach uses O(1) stack space, avoiding stack overflow.");
        } catch (Throwable t) {
            System.out.println("Failed: " + t.getMessage());
        }
        
        System.out.println("\n========================================");
    }
}
