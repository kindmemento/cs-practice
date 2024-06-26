// The Knapsack Problem is a classic optimization problem where the goal is to maximize the total value of items selected, given a weight constraint.
// There are two main variations of the problem: 0/1 Knapsack (where items cannot be divided) and Fractional Knapsack (where items can be taken in fractions).

// Steps for 0/1 Knapsack:
// 1. Initialize a 2D array `dp` where `dp[i][j]` represents the maximum value that can be achieved with the first `i` items and a knapsack capacity of `j`.
// 2. Use dynamic programming to fill the `dp` table:
// - If adding the `i`-th item exceeds the current capacity `j`, then `dp[i][j]` is the same as `dp[i-1][j]`.
// - Otherwise, consider the maximum value between including and excluding the `i`-th item: `dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i])`

// Imagine you are a thief trying to maximize the value of items you can steal from a store, but you have a limited capacity (weight) that you can carry in your knapsack.
// Each items has a specific weight and value, and the goal is to select a combination of items that maximizes the total value without exceeding the kanpsack's capacity.

public class Knapsack {

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            int weight = weights[i - 1];
            int value = values[i - 1];
            for (int j = 0; j <= capacity; j++) {
                if (j < weight) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + value);
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = { 2, 3, 4, 5 };
        int[] values = { 3, 4, 5, 6 };
        int capacity = 8;

        int maxTotalValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value that can be achieved: " + maxTotalValue);
    }
}
