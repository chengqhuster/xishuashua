package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/filling-bookcase-shelves/
 *
 * 思路简述：dp
 *
 */

public class FillingBookcaseShelves {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int len = books.length;
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            int width = 0;
            int max = 0;
            dp[i] = Integer.MAX_VALUE;
            for (int j = i; j >= 1 && width + books[j - 1][0] <= shelf_width; j--) {
                width += books[j - 1][0];
                max = Math.max(max, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + max);
            }
        }
        return dp[len];
    }
}
