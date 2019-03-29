package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * 思路简述：简单DP问题，联机算法（类似最大子序列和）
 *
 */

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int left = 0;
        int profitMax = Integer.MIN_VALUE;
        for(int i=0; i<prices.length; i++) {
            int profit = prices[i] - prices[left];
            profitMax = Math.max(profit, profitMax);
            if(prices[i] < prices[left]) {
                left = i;
            }
        }
        return profitMax;
    }
}
