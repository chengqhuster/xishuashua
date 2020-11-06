package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/reorganize-string/
 *
 * 思路简述：统计是否有过半数量的字母
 */
public class ReorganizeString {

    public String reorganizeString(String S) {
        int len = S.length();
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[S.charAt(i) - 'a']++;
        }
        int first = 0, max = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > (len + 1) / 2) {
                return "";
            }
            if (counts[i] > max) {
                max = counts[i];
                first = i;
            }
        }
        StringBuilder sb = new StringBuilder(S);
        int index = 0;
        for (int i = 0; i < counts[first]; i++) {
            sb.setCharAt(index, (char) (first + 'a'));
            index = getNextIndex(index, len);
        }
        for (int i = 0; i < counts.length; i++) {
            if (first == i) {
                continue;
            }
            for (int j = 0; j < counts[i]; j++) {
                sb.setCharAt(index, (char) (i + 'a'));
                index = getNextIndex(index, len);
            }
        }
        return sb.toString();
    }

    private int getNextIndex(int index, int len) {
        index += 2;
        if (index >= len) {
            return 1;
        }
        return index;
    }
}
