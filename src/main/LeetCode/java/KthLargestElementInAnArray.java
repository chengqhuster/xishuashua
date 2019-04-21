package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * 思路简述：第 K 大问题
 *          1. 借鉴快排的思想
 *          2. 最小堆
 *
 */

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return 0;
        }
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (true) {
            int pLeft = left;
            int pRight = right;
            while (pLeft < pRight) {
                while (pLeft < pRight && nums[pRight] <= nums[pLeft]) {
                    pRight--;
                }
                int temp = nums[pLeft];
                nums[pLeft] = nums[pRight];
                nums[pRight] = temp;
                while (pLeft < pRight && nums[pLeft] >= nums[pRight]) {
                    pLeft++;
                }
                temp = nums[pLeft];
                nums[pLeft] = nums[pRight];
                nums[pRight] = temp;
            }
            if (pLeft == k - 1) {
                return nums[pLeft];
            } else if (pLeft > k - 1) {
                right = pLeft - 1;
            } else {
                left = pLeft + 1;
            }
        }
    }
}
