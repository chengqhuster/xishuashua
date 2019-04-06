package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 思路简述：二分法
 *
 */

public class FindFirstAndLastPositionOfElementInSortedArray {
    int[] Nres = new int[]{-1, -1};

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return Nres;
        }
        int len = nums.length;
        int left = 0, right = len - 1, mid = 0;
        while (left <= right) {
            mid = (left + right)/2;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (nums[mid] != target) {
            return Nres;
        }
        left = 0;
        right = mid;
        int resLeft = 0;
        while (left <= right) {
            resLeft = (left + right)/2;
            if (nums[resLeft] < target) {
                left = resLeft + 1;
            } else {
                if (resLeft == 0|| nums[resLeft - 1] < target) {
                    break;
                } else {
                    right = resLeft - 1;
                }
            }
        }
        left = mid;
        right = len - 1;
        int resRight = 0;
        while (left <= right) {
            resRight = (left + right)/2;
            if (nums[resRight] > target) {
                right = resRight - 1;
            } else {
                if (resRight == len -1 || nums[resRight + 1] > target) {
                    break;
                } else {
                    left = resRight + 1;
                }
            }
        }
        return new int[]{resLeft, resRight};
    }
}
