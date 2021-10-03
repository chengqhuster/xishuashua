package LeetCode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 题目描述：https://leetcode.com/problems/jump-game-vii/
 *
 * 思路简述：1. index map + bfs + memory (TLE)
 *         2. DP 判断当前位置是否可达，滑动窗口统计
 */
public class JumpGameVII {

    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[0] = true;
        int prev = 0;
        for (int i = 1; i < len; i++) {
            if (i >= minJump && dp[i -minJump]) {
                prev++;
            }
            if (i > maxJump && dp[i - maxJump - 1]) {
                prev--;
            }
            dp[i] = s.charAt(i) == '0' && prev > 0;
        }
        return dp[len - 1];
    }

    public boolean canReachTimeLimitExceeded(String s, int minJump, int maxJump) {
        int len = s.length();
        if (s.charAt(len - 1) == '1') {
            return false;
        }
        // 快速定位0的位置，zeroPos是从当前位置开始第一个0所在的索引位置
        int[] zeroPos = new int[len];
        int zeroIndex = len - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                zeroIndex = i;
            }
            zeroPos[i] = zeroIndex;
        }
        // bfs
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            // 提前判断
            if (len - 1 - cur >= minJump && len - 1 - cur <= maxJump) {
                return true;
            }
            int start = cur + minJump, end = Math.min(cur + maxJump, len - 1);
            while (start <= end) {
                start = zeroPos[start];
                if (start <= end && visited.add(start)) {
                    queue.offer(start);
                }
                start = start + 1;
            }
        }
        return false;
    }
}
