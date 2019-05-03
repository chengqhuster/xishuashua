package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/spiral-matrix/
 *
 * 思路简述：使用了一个状态数组，省去了复杂的边界判断
 *
 */

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    int[][] moves = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] state = new boolean[m][n];
        int[] pos = new int[]{0, 0};
        res.add(matrix[pos[0]][pos[1]]);
        state[0][0] = true;
        int moveIndex = 0;
        while (true) {
            int i = 0;
            for (; i < 4; i++) {
                moveIndex = (moveIndex + i) % 4;
                int x = pos[0] + moves[moveIndex][0];
                int y = pos[1] + moves[moveIndex][1];
                if (x >= 0 && x < m && y >= 0 && y < n && !state[x][y]) {
                    res.add(matrix[x][y]);
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
