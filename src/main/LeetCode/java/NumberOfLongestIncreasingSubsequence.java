package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * 思路简述：参考 LongestIncreasingSubsequence 的 DP 解法
 *
 */

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] maxLen = new int[len];
        int[] count = new int[len];
        int maxSubLen = 0, res = 0;
        for (int i = 0; i < len; i++) {
            maxLen[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (maxLen[i] == maxLen[j] + 1) {
                        count[i] += count[j];
                    } else if (maxLen[i] < maxLen[j] + 1) {
                        maxLen[i] = maxLen[j] + 1;
                        count[i] = count[j];
                    }
                }
            }
            if (maxLen[i] == maxSubLen) {
                res += count[i];
            } else if (maxLen[i] > maxSubLen) {
                maxSubLen = maxLen[i];
                res = count[i];
            }
        }
        return res;
    }
}
