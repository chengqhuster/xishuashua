package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/additive-number/
 *
 * 思路简述：参考 SplitArrayIntoFibonacciSequence
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {
        int len = num.length();
        int firstNum, secondNum;
        // i 代表第一个数字串末尾位置（非包含）
        for (int i = 1; i < len; i++) {
            String first = num.substring(0, i);
            if (!zeroCheck(first)) {
                break;
            }
            try {
                firstNum = Integer.parseInt(first);
            } catch (NumberFormatException e) {
                break;
            }
            // j 代表第二个数字串末尾位置（非包含）
            for(int j = i + 1; j < len; j++) {
                String second = num.substring(i, j);
                if (!zeroCheck(second)) {
                    break;
                }
                try {
                    secondNum = Integer.parseInt(second);
                } catch (NumberFormatException e) {
                    break;
                }
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

    private boolean fibonacciCheck(String s, int first, int second, int index) {
        int next = first + second;
        if (next < 0) {
            return false;
        }
        String nextStr = String.valueOf(next);
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
