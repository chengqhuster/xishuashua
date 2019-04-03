package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * 思路简述：简单 DP
 *
 */

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int height = triangle.size();
        int[][] dp = new int[height][height];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                int a, b;
                if (isValidPos(i - 1, j - 1)) {
                    a = dp[i - 1][j - 1];
                } else {
                    a = Integer.MAX_VALUE;
                }
                if (isValidPos(i - 1, j)) {
                    b = dp[i - 1][j];
                } else {
                    b = Integer.MAX_VALUE;
                }
                dp[i][j] = Math.min(a, b) + triangle.get(i).get(j);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < height; i++) {
            res = Math.min(res, dp[height - 1][i]);
        }
        return res;
    }

    private boolean isValidPos(int x, int y) {
        return y >= 0 && y <= x;
    }
}
