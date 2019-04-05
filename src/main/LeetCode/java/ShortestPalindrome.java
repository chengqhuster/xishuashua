package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/shortest-palindrome/
 *
 * 思路简述：dp 计算出 s 子串回文状况，判断出 s 的最大回文前缀，剩余部分反转加到 s 前面即可
 *          dp 需要 N^2 的时间 与 空间，容易 MLE 或 TLE
 *
 *          每次计算 s 的最大可能的回文前缀，剩余部分反转加到 s 前面，可能的最大回文前缀在作为 s 继续判断
 *          s 为回文串时结束递归
 *
 */

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int j = 0;
        // j 可以计算出 s 的最大可能的回文前缀的长度
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
        }
        if (j == len) {
            return s;
        } else {
            String suffix = s.substring(j);
            return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
        }
    }
}
