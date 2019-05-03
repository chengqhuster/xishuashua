package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/freedom-trail/
 *
 * 思路简述：DP 对于 key 来说尾部的子串的解只与该子串本身以及 ring 此时的位置有关系，而与之前的完成 key 的部分没有关系（ring
 *          的位置与前部分的 key 有关系）
 *          dp[i][j] 代表 key 的从 i 位开始的尾子串，ring 的12点位置在 j 处时的解，dp时对于 key 要从后往前计算
 *
 */

public class FreedomTrail {
    public int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
            return 0;
        }
        int m = key.length();
        int n = ring.length();
        int[][] dp = new int[m + 1][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                // 不必算出所有的 dp[i][j]，j 位置字符与 key 在 i - 1 处的字符相符才有必要计算
                if (i == 0 && j > 0) {
                    break;
                }
                if (i > 0 && ring.charAt(j) != key.charAt(i - 1)) {
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][k] + step);
                    }
                }
            }
        }
        // m 代表按下按钮的次数
        return dp[0][0] + m;
    }
}
