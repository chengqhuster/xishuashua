package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * 思路简述：分别计算 0-k 与 k-n 的最大收益，再合并统计
 *
 */

public class BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        // 0-k最大收益
        int[] a = new int[prices.length];
        int left = 0;
        int profitMax = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            int profit = prices[i] - prices[left];
            profitMax = Math.max(profit, profitMax);
            if(prices[i] < prices[left]) {
                left = i;
            }
            a[i] = profitMax;
        }
        // k-n最大收益
        int[] b = new int[prices.length];
        int right = prices.length - 1;
        profitMax = Integer.MIN_VALUE;
        for(int i = prices.length - 1; i >= 0; i--) {
            int profit = prices[right] - prices[i];
            profitMax = Math.max(profit, profitMax);
            if(prices[i] > prices[right]) {
                right = i;
            }
            b[i] = profitMax;
        }
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, a[i] + b[i]);
        }
        return res;
    }
}
