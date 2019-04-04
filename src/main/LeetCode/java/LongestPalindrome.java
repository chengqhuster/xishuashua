package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-palindrome/
 *
 * 思路简述：
 *
 */

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] count = new int[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                count[c - 'a']++;
            } else {
                count[c - 'A' + 26]++;
            }
        }
        int res = 0;
        boolean flag = false;
        for (int n : count) {
            res += n / 2 * 2;
            if (n % 2 == 1) {
                flag = true;
            }
        }
        if (flag) {
            return res + 1;
        } else {
            return res;
        }
    }
}
