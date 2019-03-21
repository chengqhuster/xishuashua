package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/wildcard-matching/
 *
 * 思路简述：动态规划 dp[m][n] 代表 s的m长子串与p的n长子串的匹配情况
 *          边界条件 dp[0][0] = true; dp[m][0] = false(m > 0); dp[0][n] 与p的第n个元素是否为 * 相关
 *          递推式 dp[m][n] = dp[m-1][n-1]               when s[m] = p[n] or p[n] = ?
 *                dp[m][n] = dp[m][n-1] || dp[m-1][n]   when p[n] = *
 *
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j -1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
