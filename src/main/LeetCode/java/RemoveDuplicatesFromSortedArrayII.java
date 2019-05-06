package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * 思路简述：easy job
 *
 */

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int start = 1;
        boolean state = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                state = false;
                nums[start++] = nums[i];
            } else {
                if (!state) {
                    state = true;
                    nums[start++] = nums[i];
                }
            }
        }
        return start;
    }
}
