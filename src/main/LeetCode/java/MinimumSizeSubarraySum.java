package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * 思路简述：sums[i] 记录以 i 为结尾的子串和超过 s 的最小值，fromIndex[i] 记录相应的子串的起始位置
 *
 */

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] sums = new int[len];
        int[] fromIndex = new int[len];
        int from = 0, sum = 0;
        while (sum < s && from < len) {
            sum += nums[from];
            from++;
        }
        if (sum < s) {
            return 0;
        }
        int leftIndex = 0;
        while (sum >= s) {
            sum -= nums[leftIndex];
            leftIndex++;
        }
        sums[from - 1] = sum + nums[leftIndex - 1];
        fromIndex[from - 1] = leftIndex - 1;
        for (int i = from; i < len; i++) {
            int left = fromIndex[i - 1];
            int lastSum = sums[i - 1];
            while (lastSum + nums[i] >= s) {
                sums[i] = lastSum + nums[i];
                lastSum -= nums[left];
                left++;
            }
            fromIndex[i] = left - 1;
        }
        int res = len;
        for (int i = from - 1; i < len; i++) {
            res = Math.min(res, i - fromIndex[i] + 1);
        }
        return res;
    }
}
