package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/valid-parenthesis-string/
 *
 * 思路简述：1. DP  dp[i][j]代表 i 到 j（包含）的子串是否为有效配对字符串 时间复杂度 N^3
 *          2. 从左往右扫描 s，用 low 与 high 表示 s 中仍未配对的左括号的可能范围
 *
 */

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = s.charAt(i) == '*';
        }
        for (int l = 2; l <= len; l++) {
            for (int i = 0; i < len - l + 1; i++) {
                int j = i + l - 1;
                if ((s.charAt(i) == '(' && s.charAt(j) == ')')
                        || (s.charAt(i) == '(' && s.charAt(j) == '*')
                        || (s.charAt(i) == '*' && s.charAt(j) == ')')) {
                        if (l == 2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                }
                int k = i;
                while (!dp[i][j] && k < j) {
                    dp[i][j] = dp[i][k] && dp[k + 1][j];
                    k++;
                }
            }
        }
        return dp[0][len - 1];
    }

    public boolean checkValidStringSec(String s) {
        if (s == null) {
            return true;
        }
        int lo = 0, hi = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (c == '(') {
                lo++;
                hi++;
            } else if (c == ')') {
                if (lo > 0) {
                    lo--;
                }
                hi--;
            } else {
                if (lo > 0) {
                    lo--;
                }
                hi++;
            }
            if (hi < 0) {
                return false;
            }
        }
        return lo == 0;
    }
}
