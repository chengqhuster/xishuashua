package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/valid-palindrome/
 *
 * 思路简述：注意大小写字符与数字字符的判断
 *
 */

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isValidAlphanumeric(s.charAt(i))) {
                i++;
            }
            while (i < j && !isValidAlphanumeric(s.charAt(j))) {
                j--;
            }
            if (!isEqual(s.charAt(i), s.charAt(j))) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isValidAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    private boolean isEqual(char a, char b) {
        if (a == b) {
            return true;
        } else if (a >= 'a' && a <= 'z') {
            return b == a - 32;
        } else if (a >= 'A' && a <= 'Z') {
            return b == a + 32;
        } else {
            return false;
        }
    }
}
