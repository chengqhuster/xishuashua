package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-cost-for-tickets/
 *
 * 思路简述：1. dfs 从开始日期每次有三个选择，然后下一次的开始日期为跳过票价对应的时间周期后的下一个有效日期 发生 LTE 错误
 *          2. dp[i] 表示日期为 1 - i 问题的解
 *
 */

import java.util.Arrays;

public class MinimumCostForTickets {
    int res = Integer.MAX_VALUE;

    public int mincostTickets(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        boolean[] vacations = new boolean[366];
        for (int day : days) {
            vacations[day] = true;
        }
        int[] dp = new int[366];
        for (int i = 1; i <= 365; i++) {
            if (!vacations[i]) {
                dp[i] = dp[i - 1];
                continue;
            }
            int min = dp[i - 1] + costs[0];
            min = Math.min(min, dp[Math.max(0, i - 7)] + costs[1]);
            min = Math.min(min, dp[Math.max(0, i - 30)] + costs[2]);
            dp[i] = min;
        }
        return dp[365];
    }

    public int mincostTicketsDfs(int[] days, int[] costs) {
        if (days == null || days.length == 0) {
            return 0;
        }
        dfs(days, costs, days[0], 0);
        return res;
    }

    public void dfs(int[] days, int[] costs,  int start, int curCost) {
        int index = Arrays.binarySearch(days, start);
        if (index < 0) {
            index = - (index + 1);
        }
        if (index >= days.length) {
            res = Math.min(res, curCost);
        } else {
            start = days[index];
            dfs(days, costs, start + 1, curCost + costs[0]);
            dfs(days, costs, start + 7, curCost + costs[1]);
            dfs(days, costs, start + 30, curCost + costs[2]);
        }
    }
}
