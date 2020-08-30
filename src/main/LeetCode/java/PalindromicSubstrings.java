package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/palindromic-substrings/
 *
 * 思路简述：参考 PalindromePartitioning
 *
 */

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (i + 1 > j - 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }

    // 二维dp问题，空间降低到一维，nn -> 2n（1n状态不够）
    public int countSubstringsSecond(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int size = s.length();
        boolean[][] dp = new boolean[size][2];
        for (int len = 0; len < size; len++) {
            for (int start = 0; start < size - len; start++) {
                int end = start + len;
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][len%2] = start + 1 >= end - 1 || dp[start + 1][len%2];
                    if (dp[start][len%2]) {
                        res++;
                    }
                } else {
                    dp[start][len%2] = false;
                }
            }
        }
        return res;
    }
}
