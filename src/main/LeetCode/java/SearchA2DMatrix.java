package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/search-a-2d-matrix/
 *
 * 思路简述：easy job
 *
 */

import java.util.Arrays;

public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] head = new int[m];
        for (int i = 0; i < m; i++) {
            head[i] = matrix[i][0];
        }
        int index = Arrays.binarySearch(head, target);
        if (index >= 0) {
            return true;
        } else {
            index = -(index + 1);
            if (index == 0) {
                return false;
            } else {
                index = Arrays.binarySearch(matrix[index - 1], target);
                if (index >= 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
