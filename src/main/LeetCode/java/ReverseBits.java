package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/reverse-bits/
 *
 * 思路简述：位运算
 *
 */

public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int cmp = 1 << i;
            if ((n & cmp) == cmp) {
                res += 1 << (31 -i);
            }
        }
        return res;
    }
}
