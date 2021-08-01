package LeetCode.java;

import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/maximum-binary-string-after-change/
 *
 * 思路简述：忽略前置'1', 统计'0'的数量即可
 */
public class MaximumBinaryStringAfterChange {
    public String maximumBinaryString(String binary) {
        int head = binary.indexOf('0');
        if (head == -1) {
            return binary;
        } else {
            int count = 0;
            for (int i = 0; i < binary.length(); i++) {
                count += binary.charAt(i) == '0' ? 1 : 0;
            }
            char[] chars = new char[binary.length()];
            Arrays.fill(chars, '1');
            chars[head + count - 1] = '0';
            return new String(chars);
        }
    }
}
