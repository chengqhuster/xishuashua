package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/jump-game/
 *
 * 思路简述：从左往右遍历时记录最大能到达的位置，越过终点（包含）即为成功
 *
 */

public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int target = nums.length - 1;
        int max = 0;
        for (int i = 0; i <= target; i++) {
            if (i > max) {
                return false;
            } else {
                max = Math.max(max, i + nums[i]);
                if (max >= target) {
                    return true;
                }
            }
        }
        return true;
    }
}
