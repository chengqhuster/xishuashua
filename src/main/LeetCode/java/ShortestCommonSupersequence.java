package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/shortest-common-supersequence/
 *
 * 思路简述：参考 LCS 问题可以得出最短的 common super sequence，然后填入字符
 *
 */

public class ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 类 LCS
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        int len = dp[m][n];
        char[] res = new char[len];
        // 利用 dp 的状态推断 dp 的决策
        int a = m, b = n;
        while (a > 0 && b > 0) {
            if (str1.charAt(a - 1) == str2.charAt(b - 1)) {
                res[--len] = str1.charAt(a - 1);
                a--;
                b--;
            } else if (dp[a][b] == dp[a - 1][b] + 1) {
                res[--len] = str1.charAt(a - 1);
                a--;
            } else {
                res[--len] = str2.charAt(b - 1);
                b--;
            }
        }
        while (a > 0) {
            res[--len] = str1.charAt(a - 1);
            a--;
        }
        while (b > 0) {
            res[--len] = str2.charAt(b - 1);
            b--;
        }
        return new String(res);
    }
}
