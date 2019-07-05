package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/largest-sum-of-averages/
 *
 * 思路简述：直接的回溯法重复计算太多，mem 记录中间解，[index, end]区间 和 剩余分组数组合作为 key
 *
 */

public class LargestSumOfAverages {
    public double largestSumOfAverages(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        if (len < K) {
            return 0;
        }
        double[][] dp = new double[len][K + 1];
        return helper(A, dp, 0, K);
    }

    private double helper(int[] A, double[][] dp, int index, int K) {
        int len = A.length;
        if (dp[index][K] > 0) {
            return dp[index][K];
        }
        if (K == 1) {
            dp[index][K] = getAverage(A, index, len - 1);
            return dp[index][K];
        }
        double max = 0;
        for (int i = index; i < len; i++) {
            if (len - i < K) {
                break;
            }
            double left = getAverage(A, index, i);
            double right = helper(A, dp, i + 1, K - 1);
            max = Math.max(max, left + right);
        }
        dp[index][K] = max;
        return dp[index][K];
    }

    private double getAverage(int[] A, int start, int end) {
        double sum = 0;
        for (int i = start; i <= end; i++) {
            sum += A[i];
        }
        return sum/(end - start + 1);
    }
}
