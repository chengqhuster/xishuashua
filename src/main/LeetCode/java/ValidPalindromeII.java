package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/valid-palindrome-ii/
 *
 * 思路简述：直接按照回文串的性质来判断，第一次不符合的时候可以选择去掉前面或者后面的字符，再继续判断
 *
 */

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int len = s.length();
        int i = 0, j = len - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                // 判断去掉前面还是后面的元素
                return  (s.charAt(i) == s.charAt(j - 1) && isPalindrome(s, i, j - 1))
                        || (s.charAt(i + 1) == s.charAt(j) && isPalindrome(s, i + 1, j));
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
