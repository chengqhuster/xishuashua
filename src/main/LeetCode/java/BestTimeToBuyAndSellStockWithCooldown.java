package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * 思路简述：用户存在有三个状态，未持有(unhold)、持有(hold)、休息(cooldown) 三种
 *
 */

public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] unhold = new int[n];
        int[] hold = new int[n];
        int[] cool = new int[n];
        // 初始状态
        unhold[0] = 0;
        hold[0] = -prices[0];
        cool[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // 状态转移方程
            unhold[i] = Math.max(unhold[i - 1], cool[i -1]);
            hold[i] = Math.max(hold[i - 1], unhold[i - 1] - prices[i]);
            cool[i] = hold[i - 1] + prices[i];
        }
        return Math.max(unhold[n - 1], cool[n - 1]);
    }

    // 降低空间复杂度
    public int maxProfitSec(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        // 初始状态
        int unhold = 0;
        int hold = -prices[0];
        int cool = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            // 状态转移方程
            int unholdTemp = unhold;
            int holdTemp = hold;
            unhold = Math.max(unhold, cool);
            hold = Math.max(hold, unholdTemp - prices[i]);
            cool = holdTemp + prices[i];
        }
        return Math.max(unhold, cool);
    }
}
