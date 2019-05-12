package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/ugly-number-ii/
 *
 * 思路简述：easy job
 *
 */

public class UglyNumber {
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }
        while (num != 1) {
            if ((num & 1) == 0) {
                num = num >> 1;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else {
                break;
            }
        }
        return num == 1;
    }
}
