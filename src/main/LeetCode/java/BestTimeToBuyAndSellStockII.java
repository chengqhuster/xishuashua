package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 思路简述：在所有上升区间买卖
 *
 */

import java.util.Arrays;

public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int[] last = new int[1];
        last[0] = prices[0];
        return Arrays.stream(prices).reduce(0, (acc, element) -> {
            if (element > last[0]) {
                acc += element - last[0];
                last[0] = element;
                return acc;
            } else {
                last[0] = element;
                return acc;
            }
        });
    }

}
