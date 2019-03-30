package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/predict-the-winner/
 *
 * 思路简述：dp[i][j] 代表从nums的 i 到 j 这部分先手能够获得的最大利益
 *          边界条件 dp[i][i] = nums[i]
 *          转移方程 dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]) 取左或者右端点 剩余部分都是对方先手
 *          由内向外计算（button up）
 *
 */

public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int dis = 1; dis < len; dis++) {
            for (int i = 0; i < len - dis; i++) {
                int j = i + dis;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }

    // reduce space complex
    public boolean PredictTheWinnerSec(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[2][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = nums[i];
        }
        boolean flag = true;
        for (int dis = 1; dis < len; dis++) {
            for (int i = 0; i < len - dis; i++) {
                int j = i + dis;
                if (flag) {
                    dp[1][i] = Math.max(nums[i] - dp[0][i + 1], nums[j] - dp[0][i]);
                } else {
                    dp[0][i] = Math.max(nums[i] - dp[1][i + 1], nums[j] - dp[1][i]);
                }

            }
            flag = !flag;
        }
        return flag ? dp[0][0] >= 0 : dp[1][0] >= 0;
    }
}
