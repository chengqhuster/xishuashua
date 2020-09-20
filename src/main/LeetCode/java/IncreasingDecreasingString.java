package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/increasing-decreasing-string/
 *
 * 思路简述：count存储字母统计数量
 */
public class IncreasingDecreasingString {

    public String sortString(String s) {
        int[] count = new int[26];
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            total++;
        }
        StringBuffer sb = new StringBuffer();
        while (total > 0) {
            total -= sweepHigh(count, sb);
            total -= sweepLow(count, sb);
        }
        return sb.toString();
    }

    private int sweepHigh(int[] count, StringBuffer sb) {
        int used = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                count[i]--;
                sb.append((char) ('a' + i));
                used++;
            }
        }
        return used;
    }

    private int sweepLow(int[] count, StringBuffer sb) {
        int used = 0;
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] > 0) {
                count[i]--;
                sb.append((char) ('a' + i));
                used++;
            }
        }
        return used;
    }
}
