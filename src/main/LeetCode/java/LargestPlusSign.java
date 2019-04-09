package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximal-square/
 *
 * 思路简述：对于位置 i,j 从 4 个方向统计它的连续 1 的长度
 *
 */

public class LargestPlusSign {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        if (N <= 0) {
            return 0;
        }
        if (mines == null || mines.length == 0) {
            return (N + 1)/2;
        }
        int[][] matrix = new int[N][N];
        for (int[] mine : mines) {
            matrix[mine[0]][mine[1]] = 1;
        }
        int[][] dp = new int[N][N];
        // left to right
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    count++;
                    dp[i][j] = count;
                } else {
                    count = 0;
                }
            }
        }
        // right to left
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    count++;
                    dp[i][j] = Math.min(dp[i][j], count);
                } else {
                    count = 0;
                }
            }
        }
        // up to down
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (matrix[j][i] == 0) {
                    count++;
                    dp[j][i] = Math.min(dp[j][i], count);
                } else {
                    count = 0;
                }
            }
        }
        // up to down
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = N - 1; j >= 0; j++) {
                if (matrix[j][i] == 0) {
                    count++;
                    dp[j][i] = Math.min(dp[j][i], count);
                } else {
                    count = 0;
                }
            }
        }
        int res = 0;
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < N ; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
