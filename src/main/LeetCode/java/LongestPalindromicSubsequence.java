package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * 思路简述：DP[i][j] = max{
 *                          DP[i-1][j-1] + 2 (if s[i] = s[j])
 *                          DP[i-1][j]
 *                          DP[i][j-1]
 *                        }
 *
 */

public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0)  {
            return 0;
        }
        int N = s.length();
        int[][] DP = new int[N][N];
        for(int i=0; i<N; i++) {
            DP[i][i] = 1;
        }
        for(int i=1; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (j + i < N) {
                    int len = DP[j + 1][j + i - 1];
                    if (s.charAt(j) == s.charAt(j + i)) {
                        len = len + 2;
                    }
                    DP[j][j + i] = Math.max(len, Math.max(DP[j + 1][j + i], DP[j][j + i - 1]));
                }
            }
        }
        return DP[0][N-1];
    }
}
