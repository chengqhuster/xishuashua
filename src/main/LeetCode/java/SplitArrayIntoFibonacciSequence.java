package LeetCode.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目描述：https://leetcode.com/problems/split-array-into-fibonacci-sequence/
 *
 * 思路简述：穷举前两位可能的数，然后验证
 */
public class SplitArrayIntoFibonacciSequence {

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        int len = S.length();
        int firstNum, secondNum;
        // i 代表第一个数字串末尾位置（非包含）
        for (int i = 1; i < len; i++) {
            String first = S.substring(0, i);
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
                String second = S.substring(i, j);
                if (!zeroCheck(second)) {
                    break;
                }
                try {
                    secondNum = Integer.parseInt(second);
                } catch (NumberFormatException e) {
                    break;
                }
                res.clear();
                res.add(firstNum);
                res.add(secondNum);
                if (fibonacciCheck(S, firstNum, secondNum, j, res)) {
                    return res;
                }
            }
        }
        return new ArrayList<>();
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

    private boolean fibonacciCheck(String s, int first, int second, int index, List<Integer> res) {
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
        res.add(next);
        if (nextIndex == s.length()) {
            return true;
        }
        return fibonacciCheck(s, second, next, nextIndex, res);
    }
}
