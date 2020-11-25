package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/string-compression/
 *
 * 思路简述：straightforward
 */
public class StringCompression {

    public int compress(char[] chars) {
        int i = 0, j = 0, index = 0;
        while (true) {
            while (j < chars.length && chars[i] == chars[j]) {
                j++;
            }
            if (i == j) {
                return index;
            }
            int diff = j - i;
            chars[index] = chars[i];
            index++;
            if (diff > 1) {
                String num = String.valueOf(diff);
                for (int k = 0; k < num.length(); k++) {
                    chars[index] = num.charAt(k);
                    index++;
                }
            }
            i = j;
        }
    }
}
