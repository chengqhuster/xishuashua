package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximal-square/
 *
 * 思路简述：DP dp[i + 1][j + 1] 代表第以(i, j)为右下角的最大正方形边长，它与(i-1, j) (i, j -1) (i-1, j-1)相关，这三者中的
 *          最小正方形 和 (i, j)点能够拼成 边长增加 1 的正方形（空余部分可由其它两个较大的正方形填充）画图可更加明确
 *
 */

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1])  +1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res * res;
    }
}
