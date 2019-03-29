package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * 思路简述：多种DP思想 见Java注释
 *
 */

public class BestTimeToBuyAndSellStockIV {
    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */
    public int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n/2) {
            return quickSolution(prices);
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int premax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], premax + prices[j]);
                premax = Math.max(premax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }

    /**
     * 每一天的股票买卖有两种状态 持有股票和未持有股票 我们用
     * hold[i][j] 对于0~j天中最多进行i次交易并且第j天仍然持有股票的收益
     * unhold[i][j] 对于0~j天中最多进行i次交易并且第j天不持有股票的收益
     * 边界值 hold[i][0] = -price[0]
     *       unhold[0][j] = 0
     *       unhold[i][0] = 0
     * 转移方程 hold[i][j] = Math.max(hold[i][j - 1], unhold[i - 1][j] - prices[j])
     *         unhold[i][j] = Math.max(unhold[i][j - 1], hold[i][j - 1] + prices[j])
     */
    public int maxProfitSec(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n/2) {
            return quickSolution(prices);
        }
        int[][] hold = new int[k + 1][n];
        int[][] unhold = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            hold[i][0] = -prices[0];
            for (int j = 1; j < n; j++) {
                hold[i][j] = Math.max(hold[i][j - 1], unhold[i-1][j] - prices[j]);
                unhold[i][j] = Math.max(unhold[i][j - 1], hold[i][j - 1] + prices[j]);
            }
        }
        return unhold[k][n - 1];
    }

    // todo 空间由二维降低到一维 错误写法 待修正
    /**
     * 降低第二种解的空间复杂度 unhold的第j天的值 可以由 unhold的第j-1天和 hold的第j-1天的值更新
     *                        然后 hold的第j天的值 可以由 unhold的第j天和 hold的第j-1天的值更新
     */
    public int maxProfitThi(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n/2) {
            return quickSolution(prices);
        }
        int[] hold = new int[k + 1];
        int[] unhold = new int[k + 1];
        for (int p : prices) {
            hold[0] = -prices[0];
            for (int i = 1; i <= k; i++) {
                unhold[i] = Math.max(unhold[i], hold[i] + p);
                hold[i] = Math.max(hold[i], unhold[i - 1] - p);
            }
        }
        return unhold[k];
    }

    private int quickSolution(int[] prices) {
        int maxPro = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                maxPro += prices[i] - prices[i-1];
            }
        }
        return maxPro;
    }
}
