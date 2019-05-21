package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/target-sum/
 *
 * 思路简述：注意题目给的约束 The sum of elements in the given array will not exceed 1000.
 *          1. 我们可以从 sum 入手，dp 求解的个数
 *          2. 将所有元素按照所取的正负号分为两部分 和 分别为 P，N
 *          P - N = S
 *          P - N + P + N = S + P + N
 *          2 * P = S + sum
 *          P = (S + sum) / 2
 *          注意 P 为部分元素的组合即可
 *
 */

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) {
            return 0;
        }
        // dp代表从 -sum 到 sum 解的个数
        int[] dp = new int[sum * 2 + 1];
        // 0 个元素时 dp的情况
        dp[sum] = 1;
        for (int num : nums) {
            int[] next = new int[sum * 2 + 1];
            for (int i = 0; i < sum * 2 + 1; i++) {
                if (dp[i] != 0) {
                    //想想数组为什么不越界，dp[i] 不为 0，说明 i 时前面元素组合的一种解
                    //加上当前元素，整体解也会在 sum 范围内
                    next[i + num] += dp[i];
                    next[i - num] += dp[i];
                }
            }
            dp = next;
        }
        return dp[sum + S];
    }

    public int findTargetSumWaysSec(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) {
            return 0;
        }
        int target = sum + S;
        if ((target & 1) == 1) {
            return 0;
        }
        target = target >> 1;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums) {
            // 从后往前，不用担心解被覆盖的问题
            for (int i = sum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
