package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-product-subarray/
 *
 * 思路简述：dp[i] 代表 nums 的 0-i 子串的解最大值与最小值 可用 dp[i][0] 与 dp[i][1] 表示
 *          则 dp[i] 由 dp[i - 1] 以及 nums[i] 决定
 *          解答时可将一维 dp 降低到 零维，保留前一个解的最大最小值即可
 */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        int min = res, max = res;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                min = min ^ max;
                max = min ^ max;
                min = min ^ max;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
