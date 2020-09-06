package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/find-the-difference/
 *
 * 思路简述：参考 anagram 相关问题
 */
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        return (char) (getStringCountSum(t) - getStringCountSum(s));
    }

    private long getStringCountSum(String s) {
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i);
        }
        return res;
    }
}
