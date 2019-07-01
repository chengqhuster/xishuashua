package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/count-different-palindromic-subsequences/
 *
 * 思路简述：如何处理 different，map 记录数量太多了,而且常规 dp 也无法记录 remove 细节
 *          从构建回文串着手，按字母序构建回文串的元素，保证不重复，dp 作为子问题的记忆
 *          个人认为这个不是常规意义上的 dp 问题，有子问题，但是没有合并的概念
 *
 */

import java.util.TreeSet;

public class CountDifferentPalindromicSubsequences {
    int mod = 1000000007;

    public int countPalindromicSubsequences(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        TreeSet<Integer>[] cIndex = new TreeSet[26];
        for (int i = 0; i < 26; i++) {
            cIndex[i] = new TreeSet<>();
        }
        int len = S.length();
        for (int i = 0; i < len; i++) {
            cIndex[S.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[len][len];
        return mem(0, len - 1, dp, cIndex);
    }

    private int mem(int start, int end, int[][] dp, TreeSet<Integer>[] cIndex) {
        if (dp[start][end] > 0) {
            return dp[start][end];
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            Integer newLeft = cIndex[i].ceiling(start);
            Integer newRight = cIndex[i].floor(end);
            if (newLeft == null || newRight == null || newLeft > newRight) {
                continue;
            }
            ans++;
            if (newLeft < newRight) {
                ans++;
                if (newLeft + 1 <= newRight - 1) {
                    ans += mem(newLeft + 1, newRight - 1, dp, cIndex);
                }
            }
        }
        dp[start][end] = (int)ans%mod;
        return dp[start][end];
    }
}
