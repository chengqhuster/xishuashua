package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/ugly-number-ii/
 *
 * 思路简述：dp代表丑数的集合，dp[i]是由前面的解与因子的乘积中的最小值
 *          i，j，k代表三个因子的在前置位的位置。每次取最小值后向后移动
 *
 */

public class UglyNumberII {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int i = 0, j = 0, k = 0;
        for (int p = 1; p < n; p++) {
            dp[p] = Math.min(Math.min(dp[i] * 2, dp[j] * 3), dp[k] * 5);
            // 错误写法，忽略了重复值的情况
//            if (dp[p] == dp[i] * 2) {
//                i++;
//            } else if (dp[p] == dp[j] * 3) {
//                j++;
//            } else {
//                k++;
//            }
            if (dp[p] == dp[i] * 2) {
                i++;
            }
            if (dp[p] == dp[j] * 3) {
                j++;
            }
            if (dp[p] == dp[k] * 5) {
                k++;
            }
        }
        return dp[n - 1];
    }
}
