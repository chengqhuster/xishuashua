package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/search-a-2d-matrix-ii/
 *
 * 思路简述：选择右上角或者左下角为起始位置，通过与 target 的大小判断移动位置，直至命中 target
 *
 */

public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int x = 0;
        int y = matrix[0].length - 1;
        while (x < matrix.length && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else {
                if (matrix[x][y] > target) {
                    y--;
                } else {
                    x++;
                }
            }
        }
        return false;
    }
}
