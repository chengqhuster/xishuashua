package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/delete-and-earn/
 *
 * 思路简述：dp[i][j] i 到 j 构成子多边形，注意此时 i j 节点是连接在一起的
 *
 */

public class MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int subLen = 2; subLen < len; subLen++) {
            for (int start = 0; start < len - subLen; start++) {
                int end = start + subLen;
                dp[start][end] = Integer.MAX_VALUE;
                for (int k = start + 1; k < end; k++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k][end] + A[start] * A[k] * A[end]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
