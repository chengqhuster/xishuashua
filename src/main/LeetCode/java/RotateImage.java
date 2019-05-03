package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/rotate-image/
 *
 * 思路简述：旋转 确定好对应的四个点
 *
 */

public class RotateImage {
    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int end = len - 1 - i;
            for (int dis = 0; dis < end - i; dis++) {
                   int temp = matrix[i][i + dis];
                   matrix[i][i + dis] = matrix[end - dis][i];
                   matrix[end - dis][i] = matrix[end][end - dis];
                   matrix[end][end - dis] = matrix[i + dis][end];
                   matrix[i + dis][end] = temp;
            }
        }
    }
}
