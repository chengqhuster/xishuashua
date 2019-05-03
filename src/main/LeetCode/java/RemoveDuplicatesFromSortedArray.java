package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * 思路简述：easy job
 *
 */

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 1;
        int ref = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[len++] = nums[ref];
            }
            ref++;
        }
        return len;
    }
}
