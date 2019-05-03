package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/plus-one/
 *
 * 思路简述：easy job
 *
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int add = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + add;
            if (sum == 10) {
                digits[i] = 0;
                add = 1;
            } else {
                digits[i] = sum;
                add = 0;
                break;
            }
        }
        if (add == 1) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        } else {
            return digits;
        }
    }
}
