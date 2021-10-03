package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/jump-game-v/
 *
 * 思路简述：从无法跳跃的点逆向出发（明确起始状态），递归DP
 */
public class JumpGameV {

    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, dp(arr, dp, i, d));
        }
        return res;
    }

    private int dp(int[] arr, int[] dp, int index, int d) {
        if (dp[index] > 0) {
            return dp[index];
        }
        int maxDp = 0;
        int highest = arr[index];
        for (int i = -1; i >= -d && index + i >= 0; i--) {
            if (arr[index + i] > highest) {
                highest = arr[index + i];
                maxDp = Math.max(maxDp, dp(arr, dp, index + i, d));
            }
        }
        highest = arr[index];
        for (int i = 1; i <= d && index + i < arr.length; i++) {
            if (arr[index + i] > highest) {
                highest = arr[index + i];
                maxDp = Math.max(maxDp, dp(arr, dp, index + i, d));
            }
        }
        dp[index] = maxDp + 1;
        return dp[index];
    }
}
