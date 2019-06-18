package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-string-chain/
 *
 * 思路简述：dp 每个 word 只需要和比它短一长度的比较即可
 *
 */

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(words, Comparator.comparing(String::length));
        dp[0] = 1;
        int last = 0, cur = 0, i = 1;
        int curLen = words[0].length();
        while (i < n && words[i].length() == curLen) {
            i++;
        }
        // all words length is the same
        if (i == n) {
            return 1;
        }
        curLen = words[i].length();
        cur = i;
        for (; i < n; i++) {
            if (words[i].length() != curLen) {
                curLen = words[i].length();
                last = cur;
                cur = i;
            }
            if (curLen != words[last].length() + 1) {
                continue;
            }
            for (int j = last; j < cur; j++) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int p : dp) {
            res = Math.max(res ,p);
        }
        return res;
    }

    private boolean isPredecessor(String a, String b) {
        int[] c = new int[26];
        for (int i = 0; i < b.length(); i++) {
            c[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < a.length(); i++) {
            int pos = a.charAt(i) - 'a';
            if (c[pos] == 0) {
                return false;
            }
            c[pos]--;
        }
        return true;
    }
}
