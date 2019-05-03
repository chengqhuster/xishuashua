package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/spiral-matrix-ii/
 *
 * 思路简述：参考 SpiralMatrix
 *
 */

public class SpiralMatrixII {
    int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        int[][] res = new int[n][n];
        boolean[][] state = new boolean[n][n];
        int[] pos = new int[]{0, 0};
        int start = 1;
        res[0][0] = start;
        state[0][0] = true;
        int moveIndex = 0;
        while (true) {
            int i = 0;
            for (; i < 4; i++) {
                moveIndex = (moveIndex + i) % 4;
                int x = pos[0] + moves[moveIndex][0];
                int y = pos[1] + moves[moveIndex][1];
                if (x >= 0 && x < n && y >= 0 && y < n && !state[x][y]) {
                    res[x][y] = ++start;
                    pos[0] = x;
                    pos[1] = y;
                    state[x][y] = true;
                    break;
                }
            }
            if (i == 4) {
                break;
            }
        }
        return res;
    }
}
