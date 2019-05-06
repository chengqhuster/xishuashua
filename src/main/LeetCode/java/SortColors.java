package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sort-colors/
 *
 * 思路简述：easy job
 *
 */

import java.util.Arrays;

public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }
        Arrays.fill(nums, 0, count[0], 0);
        Arrays.fill(nums, count[0], count[0] + count[1], 1);
        Arrays.fill(nums, count[0] + count[1], count[0] + count[1] + count[2], 2);
    }
}
