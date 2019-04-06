package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/coin-change/
 *
 * 思路简述：简单 DP
 *
 */

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            if (Arrays.binarySearch(coins, i) >= 0) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (i - coin > 0 && dp[i - coin] > 0) {
                        min = Math.min(min, dp[i - coin] + 1);
                    }
                }
                if (min == Integer.MAX_VALUE) {
                    dp[i] = -1;
                } else {
                    dp[i] = min;
                }
            }
        }
        return dp[amount];
    }
}
