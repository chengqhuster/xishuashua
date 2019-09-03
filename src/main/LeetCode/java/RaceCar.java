package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/race-car/
 *
 * 思路简述：https://leetcode.com/problems/race-car/discuss/123834/C%2B%2BJavaPython-DP-solution
 *          还缺少一些严格的数学证明
 *
 */

public class RaceCar {
    public int racecar(int target) {
        int[] dp = new int[target + 1];
        dp[1] = 1;
        return helper(target, dp);
    }

    private int helper (int target, int[] dp) {
        if (dp[target] > 0) {
            return dp[target];
        }
        int k = 1, pos = 2;
        while (pos - 1 < target) {
            pos *= 2;
            k++;
        }
        if (target == pos - 1) {
            return k;
        }
        // k step forward, then back
        int res = k + 1 + helper(pos - 1 - target, dp);
        // k - 1 step forward, then back m steps, then forward
        for (int m = 0; m < k - 1; m++) {
            res = Math.min(res, k - 1 + 1 + m + 1 + helper((1 << m) - 1 + target - (pos / 2 - 1), dp));
        }
        dp[target] = res;
        return res;
    }
}
