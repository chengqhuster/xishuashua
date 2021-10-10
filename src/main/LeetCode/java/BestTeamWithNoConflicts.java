package LeetCode.java;

import java.util.Arrays;

/*
 * 题目描述：https://leetcode.com/problems/best-team-with-no-conflicts/
 *
 * 思路简述：动态规划，dp的构造是将队员进行一定的排序，dp[i]为前i个队员的最大分数
 *        dp[i] = max{dp[i], dp[i - k] + a[i]} where a[i - k] <= a[i]
 *        时间复杂度为 n^2 但是题目限制了队员的数量不超过 1000
 *
 */
public class BestTeamWithNoConflicts {

    public int bestTeamScore(int[] scores, int[] ages) {

        int[][] player = new int[scores.length][2];
        for (int i = 0; i < player.length; i++) {
            player[i][0] = ages[i];
            player[i][1] = scores[i];
        }
        Arrays.sort(player, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int[] dp = new int[player.length];
        dp[0] = player[0][1];
        int res = dp[0];
        for (int i = 1; i < player.length; i++) {
            dp[i] = player[i][1];
            for (int j = 0; j < i; j++) {
                if (player[i][1] >= player[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + player[i][1]);
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
