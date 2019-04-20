package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/minimum-cost-to-merge-stones/
 *
 * 思路简述：dp[i][j] 表示从 i 到 j 的最大的 K 结合（不一定最后只剩下一叠）需要的最小代价
 *          显然最后会剩下 (j - i + 1 - 1) % (k - 1) + 1 = (j - i) % (k - 1) + 1 叠
 *          dp[i][j] = 0 when j - i + 1 < k
 *
 */

public class MinimumCostToMergeStones {
    public int mergeStones(int[] stones, int K) {
        if (stones == null || stones.length == 0 || K < 1) {
            return -1;
        }
        int len = stones.length;
        if ((len - 1) % (K - 1) != 0) {
            return -1;
        }
        int[] prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + stones[i];
        }
        int[][] dp = new int[len][len];
        for (int l = K; l <= len; l++) {
            for (int i = 0; i <= len - l; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid += K -1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                // 满足 (j - i) % (K - 1) 时候会多出一次 merge ，把所有数据合成一叠
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += prefixSum[j + 1] - prefixSum[i];
                }
            }
        }
        return dp[0][len - 1];
    }
}
