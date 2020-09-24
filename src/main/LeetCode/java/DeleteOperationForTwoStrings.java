package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * 思路简述：同 LongestCommonSubsequence
 */
public class DeleteOperationForTwoStrings {

    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return  word1.length();
        }
        int[][] dp = new int[word1.length()][word2.length()];
        if (word1.charAt(0) == word2.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < word2.length(); i++) {
            if (dp[0][i - 1] == 1 || word1.charAt(0) == word2.charAt(i)) {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < word1.length(); i++) {
            if (dp[i - 1][0] == 1 || word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length() - 1][word2.length() - 1];
    }
}
