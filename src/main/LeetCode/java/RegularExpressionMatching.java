package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/regular-expression-matching/
 *
 * 思路简述：分支求解，可以考虑状态机的模式
 */
public class RegularExpressionMatching {

    private static final char DOT = '.';
    private static final char STAR = '*';

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return matchCore(s, 0, p, 0);
    }

    private boolean matchCore(String s, int sIndex, String p, int pIndex) {
        int sLen = s.length();
        int pLen = p.length();
        if (sIndex == sLen && pIndex == pLen) {
            return true;
        }
        if (pIndex + 1 < pLen && p.charAt(pIndex + 1) == STAR) {
            // match 0
            if (matchCore(s, sIndex, p, pIndex + 2)) {
                return true;
            }
            // match 1 or more
            while (sIndex < sLen && isMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                if (matchCore(s, sIndex + 1, p, pIndex + 2)) {
                    return true;
                }
                sIndex++;
            }
        } else {
            if (sIndex < sLen && pIndex < pLen && isMatch(s.charAt(sIndex), p.charAt(pIndex))) {
                return matchCore(s, sIndex + 1, p, pIndex + 1);
            }
        }
        return false;
    }

    private boolean isMatch(char a, char b) {
        return b == DOT || a == b;
    }
}
