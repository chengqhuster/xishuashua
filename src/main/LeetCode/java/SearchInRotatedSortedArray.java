package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * 思路简述：先用二分法（比较元素为 nums[0]）找出枢纽的位置 mid 变量（mid 为旋转后 后部分数组的起始位置 可能是 len）
 *          在直接用 Arrays 的二分法查找方法在两个有序序列中查找 target
 *
 */

import java.util.Arrays;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int mid = 1, left = 1, right = len - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] > nums[0]) {
                if (mid == len - 1 || nums[mid] > nums[mid + 1]) {
                    mid++;
                    break;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[0]){
                if (nums[mid] < nums[mid - 1]) {
                    break;
                } else {
                    right = mid - 1;
                }
            } else {
                mid++;
                break;
            }
        }
        int index = Arrays.binarySearch(nums, 0, mid, target);
        if (index >= 0) {
            return index;
        }
        if (mid < len) {
            index = Arrays.binarySearch(nums, mid, len, target);
            if (index >= 0) {
                return index;
            }
        }
        return -1;
    }
}
