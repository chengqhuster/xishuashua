package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/wiggle-subsequence/
 *
 * 思路简述：a. 二维 dp，一个维度是 num 的 index，一个维度是 当前位置与前者差的正负性，1 代表 pos，0 代表 neg
 *          b. 优化 a，a 中定义的 dp 一定是以 index 为结尾的子串的解，这里我们将其约定再扩大一些，dp[index][POS/NEG]
 *          表示前 index 数的子串（最后一处上升与下降）的解
 *
 */

public class WiggleSubsequence {
    private static int POSITIVE = 1;
    private static int NEGATIVE = 0;

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][NEGATIVE] = 1;
        dp[0][POSITIVE] = 1;
        return solutionA(nums, dp, len);
    }

    private int solutionA(int[] nums, int[][] dp, int len) {
        int max = 1;
        for (int i = 1; i < len; i++) {
            dp[i][NEGATIVE] = 1;
            dp[i][POSITIVE] = 1;
            for (int j = i -1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i][POSITIVE] = Math.max(dp[i][POSITIVE], dp[j][NEGATIVE] + 1);
                } else if (nums[i] < nums[j]) {
                    dp[i][NEGATIVE] = Math.max(dp[i][NEGATIVE], dp[j][POSITIVE] + 1);
                }
            }
            max = Math.max(max, Math.max(dp[i][POSITIVE], dp[i][NEGATIVE]));
        }
        return max;
    }

    private int solutionB(int[] nums, int[][] dp, int len) {
        int max = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i][POSITIVE] = dp[i - 1][NEGATIVE] + 1;
                dp[i][NEGATIVE] = dp[i - 1][NEGATIVE];
            } else if (nums[i] < nums[i - 1]) {
                dp[i][POSITIVE] = dp[i - 1][POSITIVE];
                dp[i][NEGATIVE] = dp[i - 1][POSITIVE] + 1;
            } else {
                dp[i][POSITIVE] = dp[i - 1][POSITIVE];
                dp[i][NEGATIVE] = dp[i - 1][NEGATIVE];
            }
            max = Math.max(max, Math.max(dp[i][POSITIVE], dp[i][NEGATIVE]));
        }
        return max;
    }
}
