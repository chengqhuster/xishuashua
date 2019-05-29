package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/coloring-a-border/
 *
 * 思路简述：dfs，visited 状态 与 color 状态统一起来，dic 一维数组表示方向
 *
 */

public class ColoringABorder {
    private int[] dic = new int[]{1, 0, -1, 0, 1};
    private int hei, wid;

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return grid;
        }
        hei = grid.length;
        wid = grid[0].length;
        if (!isValidPos(r0, c0)) {
            return grid;
        }
        dfs(grid, r0, c0, color);
        for (int i = 0; i < hei; i++) {
            for (int j = 0; j < wid; j++) {
                if (grid[i][j] < 0) {
                    grid[i][j] = -grid[i][j];
                }
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int r0, int c0, int color) {
        int flagColor = grid[r0][c0];
        if (flagColor < 0) {
            return;
        }
        grid[r0][c0] = -flagColor;
        for (int i = 0; i < dic.length - 1; i++) {
            int x = r0 + dic[i];
            int y = c0 + dic[i + 1];
            if (isValidPos(x, y) && (grid[x][y] < 0 || grid[x][y] == flagColor)) {
                dfs(grid, x, y, color);
            } else {
                grid[r0][c0] = -color;
            }
        }
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < hei && y >= 0 && y < wid;
    }
}
