package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/fibonacci-number/
 *
 * 思路简述：easy job
 *
 */

public class FibonacciNumber {
    public int fib(int N) {
        if (N < 2) {
            return N;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= N; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}
