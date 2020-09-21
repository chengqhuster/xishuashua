package LeetCode.java;

import java.math.BigDecimal;

/**
 * 题目描述：https://leetcode.com/problems/additive-number/
 *
 * 思路简述：参考 SplitArrayIntoFibonacciSequence，数字范围没有限制，使用BigDecimal格式
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        BigDecimal firstNum, secondNum;
        // i 代表第一个数字串末尾位置（非包含）
        for (int i = 1; i < len; i++) {
            String first = num.substring(0, i);
            if (!zeroCheck(first)) {
                break;
            }
            firstNum = new BigDecimal(first);
            // j 代表第二个数字串末尾位置（非包含）
            for(int j = i + 1; j < len; j++) {
                String second = num.substring(i, j);
                if (!zeroCheck(second)) {
                    break;
                }
                secondNum = new BigDecimal(second);
                if (fibonacciCheck(num, firstNum, secondNum, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean zeroCheck(String str) {
        if (str.charAt(0) != '0') {
            return true;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean fibonacciCheck(String s, BigDecimal first, BigDecimal second, int index) {
        BigDecimal next = first.add(second);
        String nextStr = next.toString();
        int nextIndex = index + nextStr.length();
        if (nextIndex > s.length()) {
            return false;
        }
        String cmp = s.substring(index, nextIndex);
        if (!nextStr.equals(cmp)) {
            return false;
        }
        if (nextIndex == s.length()) {
            return true;
        }
        return fibonacciCheck(s, second, next, nextIndex);
    }
}
