package LeetCode.java;

import java.util.LinkedList;

/**
 * 题目描述：https://leetcode.com/problems/jump-game-vi/
 *
 * 思路简述：1. 直接dp，优先队列做统计，超时。
 *         2. 不用优先队列，使用单调队列即可。
 */
public class JumpGameVI {

    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        // 构造单调队列
        LinkedList<Integer> queue = new LinkedList<>();
        dp[0] = nums[0];
        queue.addLast(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (i > k && queue.getFirst() == dp[i - k - 1]) {
                queue.removeFirst();
            }
            dp[i] = nums[i] + queue.getFirst();
            while (!queue.isEmpty() && queue.getLast() < dp[i]) {
                queue.removeLast();
            }
            queue.addLast(dp[i]);
        }
        return dp[nums.length - 1];
    }
}
