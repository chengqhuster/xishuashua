package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 *
 * 思路简述：计算每一行的前缀和，减少计算量，再计算两列构成的子矩阵和
 *          遍历子矩阵时，用 map 记录"前缀矩阵"和，减少计算量（用当前累积量减去目标量，作为 key 从 map 中获取解的数量）
 *
 */

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                matrix[i][j + 1] += matrix[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0);
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
