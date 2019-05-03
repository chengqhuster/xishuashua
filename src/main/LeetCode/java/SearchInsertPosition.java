package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/search-insert-position/
 *
 * 思路简述：没有重复元素，二分法查找 api（没有负返回值）
 *
 */

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int len = nums.length;
        int mid = 0, left = 0, right = len - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (nums[mid] < target) {
            return mid + 1;
        } else {
            return mid;
        }
    }
}
