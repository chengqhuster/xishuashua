package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/range-sum-query-immutable/
 *
 * 思路简述：求前缀和即可
 *
 */

public class RangeSumQueryImmutable {
    int[] sums;

    public void NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
