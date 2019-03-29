package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * 思路简述：借鉴 BestTimeToBuyAndSellStockIV 的思想
 *
 */


public class BestTimeToBuyAndSellStockWithTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int unhold = 0;
        int hold = -prices[0];
        for (int i = 0; i < prices.length; i++) {
            int tempHold = hold;
            hold = Math.max(hold, unhold - prices[i]);
            unhold = Math.max(unhold, tempHold + prices[i] - fee);
        }
        return unhold;
    }
}
