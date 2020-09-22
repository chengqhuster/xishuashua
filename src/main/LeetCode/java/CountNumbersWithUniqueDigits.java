package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/count-numbers-with-unique-digits/
 *
 * 思路简述：排列组合
 */
public class CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        // 统计不重复数字的数量，0 ~ 9...99
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // i位数字 1...0 ~ 9...9
            count += getUniqueDigitsCountForNLenNum(i);
        }
        return count;
    }

    private  int getUniqueDigitsCountForNLenNum(int n) {
        int withZero = 0, withOutZero = 0;
        // 不包含数字 0
        if (n <= 9) {
            withOutZero = amn(n, 9);
        }
        // 包含数字 0
        if (n == 1) {
            withZero = 1;
        } else if (n <= 10) {
            withZero = 9 * (n - 1) * amn(n - 2, 8);
        }
        return withOutZero + withZero;
    }

    private int amn(int m, int n) {
        int res = 1, temp = n;
        while (temp > n - m) {
            res *= temp;
            temp--;
        }
        return res;
    }
}
