package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/flood-fill/
 *
 * 思路简述：dfs
 *
 */

public class FloodFill {
    int[] directs = new int[]{1, 0 ,-1, 0, 1};
    int m, n;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return image;
        }
        m = image.length;
        n = image[0].length;
        boolean[][] status = new boolean[m][n];
        dfs(image, status, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void dfs(int[][] image, boolean[][] status, int x, int y,int source,  int newColor) {
        status[x][y] = true;
        image[x][y] = newColor;
        for (int i = 0; i < directs.length - 1; i++) {
            int p = x + directs[i];
            int q = y + directs[i + 1];
            if (isValidPos(p, q) && !status[p][q] && image[p][q] == source) {
                dfs(image, status, p, q, source, newColor);
            }
        }
    }

    private boolean isValidPos(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
