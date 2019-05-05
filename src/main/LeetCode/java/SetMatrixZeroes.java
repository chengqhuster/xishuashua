package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/set-matrix-zeroes/
 *
 * 思路简述：先标记，再清零
 *
 */

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (rows[i]) {
                for (int j = 0; j < n;j ++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n;j ++) {
            if (cols[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
