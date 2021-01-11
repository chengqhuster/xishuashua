package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 *
 * 思路简述：统计上升台阶数量即可
 */
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

    public int minNumberOperations(int[] target) {
        int res = target[0];
        for (int i = 1; i < target.length; i++) {
            res += Math.max(target[i] - target[i - 1], 0);
        }
        return res;
    }
}
