package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/integer-replacement/
 *
 * 思路简述：最大的正整数 2147483647 与 3 需要特殊处理，偶数右移一位，奇数 01 结尾时减 1，11 结尾时加 1 （3 特殊，需要减 1）
 *          或者递归暴力求解
 *
 */

public class IntegerReplacement {
    public int integerReplacement(int n) {
        int res = 0;
        if (n == 2147483647) {
            return 32;
        }
        while (n != 1) {
            if ((n & 1) == 0) {
                res++;
                n = n >> 1;
            } else {
                if (n == 3 || (n & 2) == 0) {
                    n = n - 1;
                } else {
                    n = n + 1;
                }
                res++;
            }
        }
        return res;
    }

    // too much recursion, StackOverflowError
    public int integerReplacementSec(int n) {
        if (n == 1) {
            return 0;
        } else {
            if ((n & 1) == 0) {
                return integerReplacementSec(n >> 1) + 1;
            } else {
                return Math.min(integerReplacementSec(n + 1), integerReplacementSec(n - 1)) + 1;
            }
        }
    }
}
