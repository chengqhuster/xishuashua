package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sum-of-two-integers/
 *
 * 思路简述：a + b = a ^ b + (a & b << 1)，后面的加法继续换成位运算直到a & b << 1为0
 *
 */

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        while (b != 0) {
            int c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a;
    }
}
