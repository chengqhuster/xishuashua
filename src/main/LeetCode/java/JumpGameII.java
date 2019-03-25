package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/jump-game-ii/
 *
 * 思路简述：参考JumpGame，尽量减少jump次数，到达上次jump的极限位置时
 *          增加一次jump数，上一区间的最远距离作为这一次的极限位置。
 *
 */

public class JumpGameII {
    public int jump(int[] nums) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
                if (curEnd >= nums.length) {
                    break;
                }
            }
        }
        return jumps;
    }
}
