package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/integer-break/
 *
 * 思路简述：dp
 *
 */

public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int max = 0;
            for (int k = 1; k < i; k++) {
                max = Math.max(max, k * Math.max(dp[i - k], i - k));
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
