package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximal-rectangle/
 *
 * 思路简述：二维DP 一维空间降低空间复杂度
 *          计算某一位置时，height 为底边包含该位置的最高的最大面积矩形
 *          left 与 right 为相应矩形的左右端点位置
 *          只有当前节点为 1 时，height、left、right才会向下传递
 *          注意理解 “最高” 的含义
 *
 */

import java.util.Arrays;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        int[] height = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            // from left to right
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            // from right to left
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            for (int j = 0; j < n; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }
}
