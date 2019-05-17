package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/is-subsequence/
 *
 * 思路简述：easy job
 *
 */

public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (t == null || t.length() == 0) {
            return false;
        }
        int sIndex = 0;
        int sLength = s.length();
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(sIndex) == t.charAt(i)) {
                sIndex++;
                if (sIndex == sLength) {
                    return true;
                }
            }
        }
        return false;
    }
}
