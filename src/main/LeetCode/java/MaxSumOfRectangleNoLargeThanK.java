package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * 思路简述：与 number of sub matrices that sum to target 基本相同，不过此处用红黑树而不是字典记录前缀信息，因为我们要获取的
 *          是比某地特定值大的最小量（而不是相同）
 *
 */

import java.util.TreeSet;

public class MaxSumOfRectangleNoLargeThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                matrix[i][j + 1] += matrix[i][j];
            }
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int cur = 0;
                TreeSet<Integer> tree = new TreeSet<>();
                tree.add(0);
                for (int t = 0; t < m; t++) {
                    cur += matrix[t][j] - (i > 0 ? matrix[t][i - 1] : 0);
                    Integer v = tree.ceiling(cur - k);
                    if (v != null) {
                        res = Math.max(res, cur - v);
                    }
                    tree.add(cur);
                }
            }
        }
        return res;
    }
}
