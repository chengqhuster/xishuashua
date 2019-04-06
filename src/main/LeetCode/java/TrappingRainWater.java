package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/container-with-most-water/
 *
 * 思路简述：dp[i] 表示位置 i 的前面与后面两部分中最高点的较小值，从左往右和从右往左遍历两次即可
 *
 */

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int res = 0, max = 0;
        int n = height.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.min(dp[i], max);
            max = Math.max(max, height[i]);
            if (dp[i] > height[i]) {
                res += dp[i] - height[i];
            }
        }
        return res;
    }
}
