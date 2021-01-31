package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 *
 * 思路简述：排序
 */
public class MinimumMovesToEqualArrayElementsII {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int res = 0, p = 0, q = nums.length - 1;
        while (p < q) {
            res += nums[q] - nums[p];
            p++;
            q--;
        }
        return res;
    }
}
