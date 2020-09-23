package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/longest-common-subsequence/
 *
 * 思路简述：dp
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        int[][] dp = new int[text1.length()][text2.length()];
        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < text2.length(); i++) {
            if (dp[0][i - 1] == 1 || text1.charAt(0) == text2.charAt(i)) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < text1.length(); i++) {
            if (dp[i - 1][0] == 1 || text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }
}
