package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-subarray/
 *
 * 思路简述：最长子序列和问题(不包括空序列)
 *
 */

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int maxValue = Integer.MIN_VALUE;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
            }
            sum += nums[i];
            if (sum < 0) {
                sum = 0;
            } else if (sum > res) {
                res = sum;
            }
        }
        if (res == 0) {
            return maxValue;
        } else {
            return res;
        }
    }
}
