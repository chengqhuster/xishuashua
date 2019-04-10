package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/maximum-length-of-pair-chain/
 *
 * 思路简述：1. 参考 LongestIncreasingSubsequence 的 DP 解法
 *          2. 按照 pair 的第二个元素 排序
 *
 */

import java.util.Arrays;

public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0]);
        int[] dp = new int[pairs.length];
        int res = 1;
        for (int i = 0; i < pairs.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
            res = Math.max(res, max);
        }
        return res;
    }

    public int findLongestChainSec(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1]);
        int maxEnd, res = 0;
        for (int i = 0; i < pairs.length; i++) {
            maxEnd = pairs[i][1];
            res++;
            while (i + 1 < pairs.length && pairs[i + 1][0] <= maxEnd) {
                i++;
            }
        }
        return res;
    }
}
