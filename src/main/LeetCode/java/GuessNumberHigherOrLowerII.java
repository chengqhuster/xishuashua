package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 *
 * 思路简述：参考PredictTheWinner dp[i][j] 代表数值范围为 i 到 j 时猜数的最大代价
 *          MinMax 问题 每次决策时 对方都会在剩余的集合中使你付出 Max 的代价，我们需要从所有的决策中找出最小的代价
 *          也就是 Min{Max}
 *
 */

public class GuessNumberHigherOrLowerII {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 1; len < n; len++) {
            for (int i = 1; i <= n - len; i++) {
                int j = i + len;
                int minCost = Math.min(i + dp[i + 1][j], j + dp[i][j - 1]);
                for (int k = i + 1; k < j; k++) {
                    minCost = Math.min(minCost, k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n];
    }
}
