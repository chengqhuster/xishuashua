package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/climbing-stairs/
 *
 * 思路简述：斐波那契及数列
 *
 */

public class ClimbingStairs {
    public int climbStairs(int n) {
        if ( n <= 2) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n -1];
    }
}
