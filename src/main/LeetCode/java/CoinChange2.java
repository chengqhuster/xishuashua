package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/coin-change-2/
 *
 * 思路简述：dp[i][j] 代表组成数量为 j 的数值，只用前 i 种硬币的组合数
 *          dp[i][j] 有两种策略
 *          1. 未使用第种 i 个硬币 dp[i - 1][j]
 *          2. 使用了第种 i 个硬币 dp[i][j - coins[i - 1]] when j - coins[i - 1] > 0
 *
 *          空间复杂度可以降低到一维
 *
 */

import java.util.Arrays;

public class CoinChange2 {
    // inner loop of coins cause duplication(eg. 1-2-2 and 2-1-2)
    public int changeWrong(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            int min = 0;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] > 0) {
                    min += dp[i - coin];
                }
            }
            dp[i] = min;
        }
        return dp[amount];
    }

    public int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        for (int i = 1; i <= len; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[len][amount];
    }

    // reduce space complex
    public int changeSec(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}
