package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/scramble-string/description/
 *
 * 思路简述：遍历字符串的不同拆分形式，多分支结构，递归的方式(DSF问题)
 *          进入分支后首先判断两字串的字母组成是否相同(排序在比较是否相等，或者用count数组统计a-z字母数量是否一致)
 *
 */

import java.util.Arrays;

public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length())
            return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        return isScrambleCore(c1, 0, c1.length, c2, 0, c2.length);
    }

    private boolean isScrambleCore(char[] c1, int c1Left, int c1Right, char[] c2, int c2Left, int c2Right) {
        char[] c1Sub = Arrays.copyOfRange(c1, c1Left, c1Right);
        char[] c2Sub = Arrays.copyOfRange(c2, c2Left, c2Right);
        if(Arrays.equals(c1Sub, c2Sub))
            return true;
        int[] count = new int[26];
        for(int i=c1Left; i<c1Right; i++) {
            count[c1[i] - 'a']++;
        }
        for(int i=c2Left; i<c2Right; i++) {
            if(--count[c2[i] - 'a'] < 0)
                return false;
        }
        for(int i=1; i<c1Sub.length; i++) {
            if(isScrambleCore(c1, c1Left, c1Left + i, c2, c2Left, c2Left + i) &&
                    isScrambleCore(c1, c1Left + i, c1Right, c2, c2Left + i, c2Right))
                return true;
            if(isScrambleCore(c1, c1Left, c1Left + i, c2, c2Right - i, c2Right) &&
                    isScrambleCore(c1, c1Left + i, c1Right, c2, c2Left, c2Right - i))
                return true;
        }
        return false;
    }
}
