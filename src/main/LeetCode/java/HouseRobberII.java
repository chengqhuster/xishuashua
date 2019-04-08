package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/house-robber-ii/
 *
 * 思路简述：参考 HouseRobber，将环剪开
 *
 */

import java.util.Arrays;

public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        HouseRobber robber = new HouseRobber();
        int a = robber.rob(Arrays.copyOfRange(nums, 1, len));
        int b = nums[0];
        if (len > 3) {
            b += robber.rob(Arrays.copyOfRange(nums, 2, len - 1));
        }
        return Math.max(a, b);
    }
}
