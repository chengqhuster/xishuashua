package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/divisor-game/
 *
 * 思路简述：DP
 *          数学可证明 N 为偶数时 先手必赢
 */

public class DivisorGame {
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N + 1];
        dp[1] = false;
        for (int i = 2; i <= N; i ++) {
            for (int j = 1; j <= i/2; j ++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}
