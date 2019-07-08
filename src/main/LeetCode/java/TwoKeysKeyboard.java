package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/2-keys-keyboard/
 *
 * 思路简述：a. dfs 每次要么复制要么粘贴，且上一步操作是复制的话，下一步只能粘贴
 *
 */

public class TwoKeysKeyboard {
    int min = Integer.MAX_VALUE;

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        // copy first
        dfs(n, 1, 1, 1, true);
        return min;
    }

    private void dfs(int n, int cur, int copy, int steps, boolean copied) {
        // paste
        int next = cur + copy;
        if (next == n) {
            min = Math.min(min, steps + 1);
        } else if (next < n) {
            dfs(n, next, copy, steps + 1, false);
        }
        if (!copied) {
            // copy
            dfs(n, cur, cur, steps + 1, true);
        }
    }
}

