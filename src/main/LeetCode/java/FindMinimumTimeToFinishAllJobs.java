package LeetCode.java;


import java.util.Arrays;

/**
 * 题目描述：https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 * <p>
 * 思路简述：dfs 深度便利, 注意条件 1 <= k <= jobs.length <= 12 说明 dfs 的效率并不高, 需要有效的剪枝
 * 优化1: 当前分配的最大工作时间超过已知的最小总分配时间, 停止搜索
 * 优化2: 不要分配给当前工作时间相同的工人同一种任务
 * 优化3: 对job排序, 优先分配小时间的任务(res能够下降的快)
 *
 */
public class FindMinimumTimeToFinishAllJobs {

    private int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        //优化3
        Arrays.sort(jobs);
        int[] workTimes = new int[k];
        dfs(0, jobs, workTimes);
        return res;
    }

    private void dfs(int i, int[] jobs, int[] workTimes) {
        if (i == jobs.length) {
            res = Math.min(res, getMaxWorkTime(workTimes));
            return;
        }
        // 优化1
        if (res < getMaxWorkTime(workTimes)) {
            return;
        }
        for (int j = 0; j < workTimes.length; j++) {
            //优化2
            if (j > 0 && workTimes[j] == workTimes[j - 1]) {
                continue;
            }
            workTimes[j] += jobs[i];
            dfs(i + 1, jobs, workTimes);
            workTimes[j] -= jobs[i];
        }
    }

    private int getMaxWorkTime(int[] workTimes) {
        int max = Integer.MIN_VALUE;
        for (int workTime : workTimes) {
            max = Math.max(max, workTime);
        }
        return max;
    }
}
