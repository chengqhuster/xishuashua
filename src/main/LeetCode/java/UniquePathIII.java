package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-paths-iii/
 *
 * 思路简述：dfs 遍历 从起始节点到其它节点的所有路径 暴力搜索
 *
 */

public class UniquePathIII {
    int[] moves = new int[]{1, 0, -1, 0, 1};
    int m = 0, n = 0, res = 0, left = 0;

    public int uniquePathsIII(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] state = new boolean[m][n];
        int sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 0) {
                    left++;
                }
            }
        }
        dfs(grid, sx, sy, state);
        return res;
    }

    private void dfs(int[][] grid, int x, int y, boolean[][] state) {
        if (!isValidPos(x, y) || state[x][y] || grid[x][y] == -1) {
            return;
        }
        if (grid[x][y] == 2) {
            if (left == 0) {
                res++;
            }
            return;
        }
        state[x][y] = true;
        if (grid[x][y] == 0) {
            left--;
        }
        for (int i = 0; i < moves.length - 1; i++) {
            int p = x + moves[i];
            int q = y + moves[i + 1];
            dfs(grid, p, q, state);
        }
        state[x][y] = false;
        left++;
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
