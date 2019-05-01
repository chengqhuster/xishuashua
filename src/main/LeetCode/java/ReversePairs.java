package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/reverse-pairs/
 *
 * 思路简述：分治 归并排序 参考 CountOfRangeSum
 *
 */

public class ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = mergeSort(nums, 0, nums.length);
        return res;
    }

    // 范围为左闭右开
    private int mergeSort(int[] nums, int left, int right) {
        if (right - left <= 1) {
            return 0;
        }
        int mid = (left + right) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid, right);
        int[] temp = new int[right - left];
        int leftIndex = left, rightIndex = mid, k = mid, start = 0;
        for (; leftIndex < mid; leftIndex++) {
            while (rightIndex < right && nums[rightIndex] < nums[leftIndex]) {
                temp[start++] = nums[rightIndex++];
            }
            temp[start++] = nums[leftIndex];
            // 可能超出Integer范围
//            long value = nums[leftIndex];
//            while (k < right && value > 2 * (long)nums[k]) {
//                k++;
//            }
            // 位运算 提高速度
            while (k < right && ((nums[leftIndex] << 1) > nums[k] ||
                    ((nums[leftIndex] << 1) == nums[k] && ((nums[leftIndex] & 1) == 1)))) {
                k++;
            }
            count += k - mid;
        }

        System.arraycopy(temp, 0, nums, left, start);
        return count;
    }
}
