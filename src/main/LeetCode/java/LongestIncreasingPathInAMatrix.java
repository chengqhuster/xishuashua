package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 *
 * 思路简述：dfs + memory
 *
 */

public class LongestIncreasingPathInAMatrix {
    private int[][] moves = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(i, j, matrix, mem);
                max = Math.max(max, len);
            }
        }
        return max;
    }

    private int dfs(int x, int y, int[][] matrix, int[][] mem) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (mem[x][y] != 0) {
            return mem[x][y];
        }
        int max = 1;
        for (int[] move : moves) {
            int xx = x + move[0];
            int yy = y + move[1];
            if (xx >= 0 && xx < m && yy >= 0 && yy < n && matrix[xx][yy] < matrix[x][y]) {
                max = Math.max(max, 1 + dfs(xx, yy, matrix, mem));
            }
        }
        mem[x][y] = max;
        return max;
    }
}
