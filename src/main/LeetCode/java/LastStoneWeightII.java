package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/last-stone-weight-ii/
 *
 * 思路简述：参考 https://leetcode.com/problems/last-stone-weight-ii/discuss/294888/JavaC%2B%2BPython-Easy-Knapsacks-DP
 *           背包问题，dp 是所有组合的可能的和
 *
 */

public class LastStoneWeightII {
    public int lastStoneWeightII(int[] stones) {
        boolean[] dp = new boolean[3001];
        dp[0] = true;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
            for (int i = sum; i >= stone; i--) {
                dp[i] |= dp[i - stone];
            }
        }
        for (int i = sum / 2; i > 0; i--) {
            if (dp[i]) {
                return sum - 2 * i;
            }
        }
        return 0;
    }
}
