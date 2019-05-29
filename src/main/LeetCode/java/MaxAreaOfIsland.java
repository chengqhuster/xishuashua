package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/max-area-of-island/
 *
 * 思路简述：dfs
 *
 */

public class MaxAreaOfIsland {
    int m, n;
    int[] directs = new int[]{0, 1, 0, -1, 0};

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int res = 0;
        m = grid.length;
        n = grid[0].length;
        boolean[][] status = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !status[i][j]) {
                    res = Math.max(res, dfs(grid, status, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, boolean[][] status, int x, int y) {
        if (!isValidPos(x, y) || grid[x][y] == 0 || status[x][y]) {
            return 0;
        }
        status[x][y] = true;
        int sum = 1;
        for (int i = 0; i < directs.length - 1; i++) {
            int p = x + directs[i];
            int q = y + directs[i + 1];
            sum += dfs(grid, status, p, q);
        }
        return sum;
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
