package LeetCode.java;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目描述：https://leetcode.com/problems/maximum-performance-of-a-team/
 *
 * 思路简述：按照效率降序排列，速度存在在优先队列，team大小超过k时，优先队列中剔除最小的速度
 */
public class MaximumPerformanceOfATeam {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.sort(engineers, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long res = 0, speedSum = 0;
        for (int[] engineer : engineers) {
            speedSum += engineer[0];
            pq.add(engineer[0]);
            if (pq.size() > k) {
                speedSum -= pq.poll();
            }
            res = Math.max(res, speedSum * engineer[1]);
        }
        return (int) (res % (long)(1e9 + 7));
    }
}
