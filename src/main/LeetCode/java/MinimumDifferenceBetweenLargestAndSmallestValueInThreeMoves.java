package LeetCode.java;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves
 *
 * 排序，然后按照去掉3个元素后剩余元素的数量作为index距离，取最小差
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int dis = nums.length - 4;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - dis; i++) {
            min = Math.min(min, nums[i + dis] - nums[i]);
        }
        return min;
    }
}
