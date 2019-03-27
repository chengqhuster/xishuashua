package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-1-bits/
 *
 * 思路简述：参考 counting bits
 *
 */

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int res = 0;
        while ( n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }
}
