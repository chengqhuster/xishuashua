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
                // remove front
                int ii = i + 1;
                int jj = j;
                while (ii < jj) {
                    if (s.charAt(ii) == s.charAt(jj)) {
                        ii++;
                        jj--;
                    } else {
                        break;
                    }
                }
                if (ii >= jj) {
                    return true;
                }
                // remove back
                j--;
                while (i < j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        i++;
                        j--;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }
}
