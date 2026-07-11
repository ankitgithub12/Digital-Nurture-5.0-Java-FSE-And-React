package exercise7;

import java.util.Map;

/**
 * FinancialForecasting provides recursive and optimized algorithms to predict 
 * future values of investments/assets based on growth rates.
 */
public class FinancialForecasting {

    /**
     * 1. Simple Recursive Approach (Constant Growth Rate)
     * Calculates the future value recursively.
     * Formula: FV = PV * (1 + r)^n
     * Recursive relation: FV(n) = FV(n-1) * (1 + r), with FV(0) = PV
     *
     * @param presentValue The initial value (PV).
     * @param growthRate The growth rate per period (r) (e.g., 0.05 for 5%).
     * @param periods The number of periods/years (n).
     * @return The future value after n periods.
     */
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: If no periods remain, future value is the current value
        if (periods <= 0) {
            return presentValue;
        }
        // Recursive case: FV(n) = FV(n-1) * (1 + growthRate)
        return calculateFutureValue(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    /**
     * 2. Recursive Approach (Varying Growth Rates)
     * Predicts future value applying a sequence of varying growth rates period-by-period.
     *
     * @param presentValue The starting value.
     * @param growthRates Array of growth rates (e.g., [0.05, -0.02, 0.08]).
     * @param index The current period index we are applying.
     * @return The future value after applying all rates up to the end of the array.
     */
    public static double calculateFutureValueVarying(double presentValue, double[] growthRates, int index) {
        // Base case: If we have applied all rates, return current value
        if (growthRates == null || index >= growthRates.length || index < 0) {
            return presentValue;
        }
        // Recursive case: Apply rate at index, and proceed to next index
        double nextValue = presentValue * (1 + growthRates[index]);
        return calculateFutureValueVarying(nextValue, growthRates, index + 1);
    }

    /**
     * Helper to compute the average of past growth rates.
     * This average can be used to run a standard recursive projection.
     *
     * @param pastRates Array of historical growth rates.
     * @return The average growth rate.
     */
    public static double calculateAverageRate(double[] pastRates) {
        if (pastRates == null || pastRates.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (double rate : pastRates) {
            sum += rate;
        }
        return sum / pastRates.length;
    }

    /**
     * 3. Optimized Recursive Approach (Memoized)
     * Uses a Map to cache results for specific period counts, avoiding redundant stack evaluations
     * when querying multiple subproblems or evaluating identical states.
     * Note: In a linear relation like FV(n) = FV(n-1)*(1+r), memoization optimizes duplicate top-level queries.
     *
     * @param presentValue The starting value (PV).
     * @param growthRate The growth rate per period.
     * @param periods The number of periods.
     * @param memo Cache to store computed future values for each period index.
     * @return The future value after n periods.
     */
    public static double calculateFutureValueMemoized(double presentValue, double growthRate, int periods, Map<Integer, Double> memo) {
        // Base case
        if (periods <= 0) {
            return presentValue;
        }
        // Check cache
        if (memo.containsKey(periods)) {
            return memo.get(periods);
        }
        // Compute and store in cache
        double result = calculateFutureValueMemoized(presentValue, growthRate, periods - 1, memo) * (1 + growthRate);
        memo.put(periods, result);
        return result;
    }

    /**
     * 4. Optimized Iterative Approach
     * Fully optimizes the recursive approach by converting it to a loop.
     * This avoids StackOverflowError in languages like Java which do not have 
     * automatic Tail-Call Optimization (TCO), reducing space complexity to O(1).
     *
     * @param presentValue The starting value.
     * @param growthRate The constant annual growth rate.
     * @param periods The number of periods.
     * @return The predicted future value.
     */
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int periods) {
        double futureValue = presentValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }
}
