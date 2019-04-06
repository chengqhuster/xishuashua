package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/burst-balloons/
 *
 * 思路简述：参考 https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
 *
 */

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int[] num = new int[nums.length + 2];
        int n = 1;
        for (int x : nums) {
            if (x > 0) {
                num[n++] = x;
            }
        }
        num[0] = num[n++] = 1;
        int[][] dp = new int[n][n];
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], num[i] * num[k] * num[j] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
