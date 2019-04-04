package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * 思路简述：可参考 PalindromePartitioning 只需计算每种划分方式的最小划分次数（栈的大小）
 *          也可以直接进行DP dp[i] = min{dp[j] + 1} when [j+1, i] is palindrome
 *          需要辅助数据保存 s 子串的回文状态（参考 PalindromePartitioning）
 *
 */

public class PalindromePartitioningII {
    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] state = new boolean[len][len];
        int[] minCut = new int[len];
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (j + 1 > i - 1 || state[j + 1][i - 1])) {
                    state[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, minCut[j - 1] + 1);
                }
            }
            minCut[i] = min;
        }
        return minCut[len - 1];
    }
}
