package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/decoded-string-at-index/
 *
 * 思路简述：总长度为 len = (((s1) * a + (s2)) * b + s3) * c + ... + (sn)
 */
public class DecodedStringAtIndex {

    public String decodeAtIndex(String S, int K) {
        List<String> strList = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        List<Long> sumList = new ArrayList<>();
        int count = 0, startPos = 0;
        while (true) {
            int strStart = startPos;
            while (startPos < S.length() && isLowerCaseLetter(S.charAt(startPos))) {
                startPos++;
            }
            strList.add(S.substring(strStart, startPos));
            int numStart = startPos;
            if (numStart == S.length()) {
                numList.add(1);
            } else {
                numList.add(S.charAt(startPos) - '0');
                startPos++;
            }
            long lastSum = count == 0 ? 0 : sumList.get(count - 1);
            long lastNum = count == 0 ? 0 : numList.get(count - 1);
            sumList.add(strList.get(count).length() + lastNum * lastSum);
            count++;
            if (startPos >= S.length() || sumList.get(count - 1) > Integer.MAX_VALUE) {
                strList.add("");
                sumList.add(strList.get(count).length() + sumList.get(count - 1) * numList.get(count - 1));
                break;
            }
        }

        long pos = K;
        for (int index = count; index >= 0; index--) {
            pos = pos % sumList.get(index);
            long diff = (sumList.get(index) - pos) % sumList.get(index);
            long strLen = strList.get(index).length();
            if (diff < strLen) {
                return String.valueOf(strList.get(index).charAt((int) (strLen - 1 - diff)));
            }
        }
        throw new IllegalStateException("Unreachable");
    }

    private boolean isLowerCaseLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean isDigitLetter(char c) {
        return c >= '0' && c <= '9';
    }
}
