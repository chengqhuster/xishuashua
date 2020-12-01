package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/count-substrings-that-differ-by-one-character/
 *
 * 思路简述：dp
 * dp[i][j] 表示s字符串以i位为结尾的子字符串与t字符串以j位为结尾的子字符串的分析结果
 * dp[i][j][0] 表示完全相同的子字符串数量
 * dp[i][j][1] 表示相差1个字符的子字符串数量
 */
public class CountSubstringsThatDifferByOneCharacter {

    public int countSubstrings(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int[][][] dp = new int[sLen][tLen][2];
        int res = 0;
        //初始化
        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) == t.charAt(0)) {
                dp[i][0][0] = 1;
                dp[i][0][1] = 0;
            } else {
                dp[i][0][0] = 0;
                dp[i][0][1] = 1;
            }
            res += dp[i][0][1];
        }
        for (int j = 0; j < tLen; j++) {
            if (s.charAt(0) == t.charAt(j)) {
                dp[0][j][0] = 1;
                dp[0][j][1] = 0;
            } else {
                dp[0][j][0] = 0;
                dp[0][j][1] = 1;
            }
            res += dp[0][j][1];
        }
        // 重复计算
        res -= dp[0][0][1];
        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < tLen; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    dp[i][j][1] = dp[i - 1][j - 1][1];
                } else {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = dp[i - 1][j - 1][0] + 1;
                }
                res += dp[i][j][1];
            }
        }
        return res;
    }
}
