package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/ones-and-zeroes/
 *
 * 思路简述：a. dp，改进 遇到不连续的地方作为断点 分割处理
 *
 */

public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundStringImp(String p) {
        int start = 0, len = p.length();
        boolean[][] mem = new boolean[26][len];
        int[] maxLen = new int[26];
        int res = 0;
        while (start < len) {
            int index = start, count = 0;
            while (index + 1 < len && p.charAt(index) == getLastChar(p.charAt(index + 1))) {
                index++;
            }
            for (int i = start; i <= index; i++) {
                for (int j = i + maxLen[p.charAt(i) - 'a']; j <= index; j++) {
                    if (!mem[p.charAt(i) - 'a'][j - i]) {
                        mem[p.charAt(i) - 'a'][j - i] = true;
                        count++;
                    }
                }
                if (len - i > maxLen[p.charAt(i) - 'a']) {
                    maxLen[p.charAt(i) - 'a'] = index - i;
                }
            }
            res += count;
            start = index + 1;
        }
        return res;
    }

    public int findSubstringInWraproundString(String p) {
        int len = p.length();
        boolean[][] mem = new boolean[26][len + 1];
        int res = 0;
        for (int i = 0; i < len; i++) {
            res += uniqueSubstringCount(p, i, mem);
        }
        return res;
    }

    private int uniqueSubstringCount(String p, int index, boolean[][] mem) {
        int count = 0;
        if (!mem[p.charAt(index) - 'a'][1]) {
            mem[p.charAt(index)- 'a'][1] = true;
            count++;
        }
        for (int start = index - 1; start >= 0; start--) {
            if (p.charAt(start) == getLastChar(p.charAt(start + 1))) {
                if (!mem[p.charAt(start) - 'a'][index - start + 1]) {
                    mem[p.charAt(start) - 'a'][index - start + 1] = true;
                    count++;
                }
            } else {
                break;
            }
        }
        return count;
    }

    private char getLastChar(int c) {
        if (c > 'a') {
            return (char)(c - 1);
        }
        return 'z';
    }
}
