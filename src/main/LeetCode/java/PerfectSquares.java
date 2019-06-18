package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/perfect-squares/
 *
 * 思路简述：dp, 相关的数学理论 Legendre's three-square theorem
 *
 */

import java.util.Arrays;

public class PerfectSquares {
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int k = 0;
            while (true) {
                k++;
                int p = k * k;
                if (p > i) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - p] + 1);
            }
        }
        return dp[n];
    }
}
