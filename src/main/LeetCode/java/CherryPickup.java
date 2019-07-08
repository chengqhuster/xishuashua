package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/cherry-pickup/
 *
 * 思路简述：首先单独的一次 pick 从左上到右下 或者 从右下到左上 的最大收益都是相同的，但是 cherry 只能 pick
 *          一次，所以两次不能割裂开来，我们把问题等价为两次 左上到右下 的采集，但是有 cherry 的地方只有一次收益
 *          dp[a][b][c][d] 解空间为两次采集的坐标位置 (a,b) (c,d) 每次只能向右或向下移动 所以有 a + b = c + d
 *          因此 dp 的维度可以降低到三维
 *
 *          dp 维度还可以降至二维，怎么去抽象，待思考
 *
 */

import java.util.Arrays;

public class CherryPickup {
    public int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0];
        for (int a = 0;  a < n; a++) {
            for (int b = 0; b < n; b++) {
                for (int c = 0; c < Math.min(n, a + b + 1); c++) {
                    int d = a + b - c;
                    if (dp[a][b][c] > 0 || d >= n || grid[a][b] == -1 || grid[c][d] == -1) {
                        continue;
                    }
                    int max = Integer.MIN_VALUE;
                    if (a > 0) {
                        max = Math.max(max, dp[a - 1][b][c]);
                        if (c > 0) {
                            max = Math.max(max, dp[a - 1][b][c - 1]);
                        }
                    }
                    if (b > 0) {
                        max = Math.max(max, dp[a][b - 1][c]);
                        if (c > 0) {
                            max = Math.max(max, dp[a][b - 1][c - 1]);
                        }
                    }
                    if (max < 0) {
                        continue;
                    }
                    dp[a][b][c] = max + grid[a][b];
                    if (a != c) {
                        dp[a][b][c] += grid[c][d];
                    }
                }
            }
        }
        return Math.max(dp[n - 1][n - 1][n - 1], 0);
    }
}
