package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/majority-element/
 *
 * 思路简述：找到数组的中位数，也就是数组的第 K 大数的问题（借鉴快排分治的思想）
 *
 */

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int target = nums.length/2;
        int left = 0;
        int right = nums.length - 1;
        int index = partition(nums, left, right);
        while (index != target) {
            if (index < target) {
                left = index + 1;
                index = partition(nums, left, right);
            } else {
                right = index - 1;
                index = partition(nums, left, right);
            }
            if (nums[index] == nums[target]) {
                break;
            }
        }
        return nums[target];
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    //另一种解
    public int majorityElementSec(int[] nums) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                res = nums[i];
                count = 1;
            } else {
                if (nums[i] == res) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return res;
    }
}
