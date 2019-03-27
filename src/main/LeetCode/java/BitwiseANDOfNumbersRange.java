package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/bitwise-and-of-numbers-range/
 *
 * 思路简述：位运算
 *
 */

public class BitwiseANDOfNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int step = 0;
        while (m != n) {
            m = m >> 1;
            // 提前结束
            if (m == 0) {
                break;
            }
            n = n >> 1;
            step++;
        }
        return m << step;
    }
}
