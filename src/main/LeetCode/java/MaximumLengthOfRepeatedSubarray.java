package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * 思路简述：简单DP
 *
 */

public class MaximumLengthOfRepeatedSubarray {
    public int findLength(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}
