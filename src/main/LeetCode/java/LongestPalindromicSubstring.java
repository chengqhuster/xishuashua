package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-palindromic-substring/
 *
 * 思路简述：从内往外拓展的方式，分奇数长度和偶数长度的情况讨论
 *
 */

public class LongestPalindromicSubstring {
    private int left;
    private int right;
    private int len;
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        for(int i=0; i<s.length(); i++) {
            expandPalindromicString(s, i, i);
            expandPalindromicString(s, i, i + 1);
        }
        return s.substring(left, right + 1);
    }

    private void expandPalindromicString(String s, int start, int end) {
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        if(end - start - 1 > len) {
            len = end - start - 1;
            left = start + 1;
            right = end - 1;
        }
    }
}
