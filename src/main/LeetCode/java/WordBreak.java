package LeetCode.java;


/*
 * 题目描述：https://leetcode.com/problems/word-break/
 *
 * 思路简述：dp[i] 为 s 的 0-i 的子串的解，dp[i] = true if dp[j] = true and s.sub(i, j + 1) in wordDict
 *
 */

import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            String sub  = s.substring(i);
            for (String word : wordDict) {
                if (sub.startsWith(word)) {
                    dp[i + word.length()] |= dp[i];
                }
            }
        }
        return dp[len];
    }
}
