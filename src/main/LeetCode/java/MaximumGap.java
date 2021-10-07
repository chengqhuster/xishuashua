package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/maximum-gap/
 *
 * 思路简述：基于桶排序的思路，假设nums中最大的为max，最小的为min，那么最大的gap不会小于 ceil [(max - min) / (n - 1)] (向上取整)
 *         我们将序列分为 n - 1 个桶，然后将数据分桶，只需要维护每个桶里面最大的和最小的元素（maxGap一定不会在桶内元素中产生）
 */
public class MaximumGap {

    public int maximumGap(int[] nums) {

        if (nums == null || nums.length < 2) {
            return 0;
        }
        int n = nums.length;
        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        // 向上取整
        int bucketSize = (int) Math.ceil((double) (max - min) / (n - 1));
        // min 和 max 不入桶
        int[] bucketsMin = new int[n - 1];
        int[] bucketsMax = new int[n - 1];
        Arrays.fill(bucketsMin, Integer.MAX_VALUE);
        Arrays.fill(bucketsMax, Integer.MIN_VALUE);
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            int idx = (num - min) / bucketSize;
            bucketsMin[idx] = Math.min(bucketsMin[idx], num);
            bucketsMax[idx] = Math.max(bucketsMax[idx], num);
        }
        int maxGap = Integer.MIN_VALUE, prev = min;
        for (int i = 0; i < n - 1; i++) {
            if (bucketsMin[i] == Integer.MAX_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketsMin[i] - prev);
            prev = bucketsMax[i];
        }
        maxGap = Math.max(maxGap, max - prev);
        return maxGap;
    }
}
