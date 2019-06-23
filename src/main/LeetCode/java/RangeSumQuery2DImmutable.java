package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * 思路简述：求每列的前缀后
 *
 */

public class RangeSumQuery2DImmutable {
    int[][] sums;

    public void NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        sums = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sums[i][j] = matrix[i][j] + (j == 0 ? 0 : sums[i][j - 1]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            res += sums[i][col2] - (col1 == 0 ? 0 : sums[i][col1 - 1]);
        }
        return res;
    }
}
