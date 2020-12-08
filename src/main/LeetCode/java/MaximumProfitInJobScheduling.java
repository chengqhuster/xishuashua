package LeetCode.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 题目描述：https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 *
 * 思路简述：dp[time] = profit 表示前time范围的时间最大的收益
 */
public class MaximumProfitInJobScheduling {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobs = new int[startTime.length][3];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // 这里比较关键, 按照完成时间排序
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1]));
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int sumProfit = dp.floorEntry(job[0]).getValue() + job[2];
            // 此时job[1]一定是最大的(排序过), 收益也更多的时候更新dp
            if (sumProfit > dp.lastEntry().getValue()) {
                dp.put(job[1], sumProfit);
            }
        }
        return dp.lastEntry().getValue();
    }
}
