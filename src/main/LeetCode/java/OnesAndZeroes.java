package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/ones-and-zeroes/
 *
 * 思路简述：a. dfs 每个单词决定是否要完成 TLE 2^n
 *          b. dp m*n*k
 *
 */

public class OnesAndZeroes {
    int res = 0;

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        dfs(strs, 0, 0, m, n);
        return res;
    }

    private void dfs(String[] strs, int index, int count, int mLeft, int nLeft) {
        if (index >= strs.length) {
            return;
        }
        int[] counts = getCounts(strs[index]);
        // try to complete this word
        if (counts[0] <= mLeft && counts[1] <= nLeft) {
            res = Math.max(res, count + 1);
            dfs(strs, index + 1, count + 1, mLeft - counts[0], nLeft - counts[1]);
        }
        // pass this word
        dfs(strs, index + 1, count, mLeft, nLeft);
    }

    private int[] getCounts(String s) {
        int zeros = 0, ones = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0') {
                zeros++;
            } else if (c == '1') {
                ones++;
            }
        }
        return new int[]{zeros, ones};
    }

    public int findMaxFormByDP(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] counts = getCounts(s);
            int xDiff = counts[0];
            int yDiff = counts[1];
            // back to head
            for (int i = m - xDiff; i >= 0; i--) {
                for (int j = n - yDiff; j >= 0; j--) {
                    dp[i + xDiff][j + yDiff] = Math.max(dp[i + xDiff][j + yDiff], dp[i][j] + 1);
                    res = Math.max(res, dp[i + xDiff][j + yDiff]);
                }
            }
        }
        return res;
    }
}
