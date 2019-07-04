package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/split-array-largest-sum/
 *
 * 思路简述：参考 Largest Sum of average
 *
 */

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0 || nums.length < m) {
            return 0;
        }
        int len = nums.length;
        int[] sum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int[][] dp = new int[len][m + 1];
        return helper(sum, dp, 0, m);
    }

    private int helper(int[] sum, int[][] dp, int start, int m) {
        if (dp[start][m] > 0) {
            return dp[start][m];
        }
        int len = sum.length - 1;
        if (m == 1) {
            dp[start][1] = sum[len] - sum[start];
            return dp[start][1];
        }
        int min = Integer.MAX_VALUE;
        for (int next = start; next < len; next++) {
            if (len - next < m) {
                break;
            }
            int temp = Math.max(sum[next + 1] - sum[start], helper(sum, dp, next + 1, m - 1));
            min = Math.min(min, temp);
        }
        dp[start][m] = min;
        return dp[start][m];
    }
}
