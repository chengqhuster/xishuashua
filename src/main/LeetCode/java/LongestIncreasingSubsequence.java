package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * 思路简述：方法一 是简单的 DP
 *          方法二 见注释
 *
 */

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int res = 0;
        for (int i = 1; i < len; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            res = Math.max(res, max);
        }
        return res;
    }

    public int lengthOfLISSec(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] minLTS = new int[nums.length];
        // 递增子序列的最大长度
        int len = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(minLTS, 0, len, num);
            if (index < 0) {
                index = -(index + 1);
            }
            // 用较小的数替换掉当前的值（或是序列新增的数）
            minLTS[index] = num;
            // 能够在序列最右端插入（比minLTS[len - 1]大）序列长度增加
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}
