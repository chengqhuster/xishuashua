package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/next-permutation/
 *
 * 思路简述：找出最长的单调非增的尾序列
 *
 */

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int index = nums.length - 1;
        while (index > 0 && nums[index] <= nums[index - 1]) {
            index--;
        }
        if (index == 0) {
            Arrays.sort(nums);
        } else {
            int a = nums[index - 1];
            for (int i = nums.length - 1; i >= index; i--) {
                if (nums[i] > a) {
                    nums[index - 1] = nums[i];
                    nums[i] = a;
                    break;
                }
            }
            Arrays.sort(nums, index, nums.length);
        }
    }
}
