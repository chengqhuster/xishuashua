package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-window-substring/
 *
 * 思路简述：与 MinimumSizeSubarraySum 想法如出一辙
 *
 */

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        int tNum = 0;
        int[] tCount = new int[52];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                tNum++;
                tCount[c - 'A']++;
            }
            if (c >= 'a' && c <= 'z') {
                tNum++;
                tCount[c - 'a' + 26]++;
            }
        }
        int[] sCount = new int[52];
        int validCount = 0, left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z' && tCount[c - 'A'] > 0) {
                sCount[c - 'A']++;
                if (sCount[c - 'A'] <= tCount[c - 'A']) {
                    validCount++;
                    if (validCount == tNum) {
                        right = i;
                        break;
                    }
                }
            }
            if (c >= 'a' && c <= 'z' && tCount[c - 'a' + 26] > 0) {
                sCount[c - 'a' + 26]++;
                if (sCount[c - 'a' + 26] <= tCount[c - 'a' + 26]) {
                    validCount++;
                    if (validCount == tNum) {
                        right = i;
                        break;
                    }
                }
            }
        }
        if (validCount != tNum) {
            return "";
        }
        for (int i = 0; i <= right; i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z' && tCount[c - 'A'] > 0) {
                if (sCount[c - 'A'] == tCount[c - 'A']) {
                    left = i;
                    break;
                } else {
                    sCount[c - 'A']--;
                }
            }
            if (c >= 'a' && c <= 'z' && tCount[c - 'a' + 26] > 0) {
                if (sCount[c - 'a' + 26] == tCount[c - 'a' + 26]) {
                    left = i;
                    break;
                } else {
                    sCount[c - 'a' + 26]--;
                }
            }
        }
        int[] res = new int[]{left, right};
        for (int i = right + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z' && tCount[c - 'A'] > 0) {
                sCount[c - 'A']++;
            }
            if (c >= 'a' && c <= 'z' && tCount[c - 'a' + 26] > 0) {
                sCount[c - 'a' + 26]++;
            }
            while (true) {
                c = s.charAt(left);
                if (c >= 'A' && c <= 'Z' && tCount[c - 'A'] > 0) {
                    if (sCount[c - 'A'] == tCount[c - 'A']) {
                        break;
                    } else {
                        sCount[c - 'A']--;
                    }
                }
                if (c >= 'a' && c <= 'z' && tCount[c - 'a' + 26] > 0) {
                    if (sCount[c - 'a' + 26] == tCount[c - 'a' + 26]) {
                        break;
                    } else {
                        sCount[c - 'a' + 26]--;
                    }
                }
                left++;
            }
            if (i - left < res[1] - res[0]) {
                res[0] = left;
                res[1] = i;
            }
        }
        return s.substring(res[0], res[1] + 1);
    }
}
