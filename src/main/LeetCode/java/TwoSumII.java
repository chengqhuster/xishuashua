package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * 思路简述：首位指针
 *
 */

public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int right = numbers.length - 1;
        int left = 0;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return null;
    }
}
