package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/delete-and-earn/
 *
 * 思路简述：二维 dp dp[i][j] i,j 为 nums 的 distinct 排序后的子序列 TLE
 *          优化：没有连续的地方作为断点将问题分解为独立的子问题 (TIME < 5.04 %)
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> counts = new HashMap<>(8);
        for (int num : nums) {
            counts.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        List<Integer> lists = counts.keySet().stream().sorted().collect(Collectors.toList());
        int res = 0;
        int start = 0;
        while (start < lists.size()) {
            int end = start;
            int diff = 0;
            while (end < lists.size() && lists.get(end) == lists.get(start) + diff) {
                end++;
                diff++;
            }
            res += helper(lists.subList(start, end), counts);
            start = end;
        }
        return res;
    }

    private int helper(List<Integer> lists, HashMap<Integer, Integer> counts) {
        int len = lists.size();
        int[][] dp = new int[len][len];
        for (int i = 0; i < lists.size(); i++) {
            dp[i][i] = lists.get(i) * counts.get(lists.get(i));
        }
        for (int subLen = 2; subLen <= len; subLen++) {
            for (int start = 0; start < len + 1 - subLen; start++) {
                int end = start + subLen - 1;
                int max = 0;
                for (int k = start; k <= end; k++) {
                    int kLeft = k - 1;
                    if (kLeft >= 0 && lists.get(k) == lists.get(kLeft) + 1) {
                        kLeft--;
                    }
                    int kRight = k + 1;
                    if (kRight < len && lists.get(k) == lists.get(kRight) - 1) {
                        kRight++;
                    }
                    int left = 0, mid, right = 0;
                    mid = lists.get(k) * counts.get(lists.get(k));
                    if (kLeft >= start) {
                        left = dp[start][kLeft];
                    }
                    if (kRight <= end) {
                        right = dp[kRight][end];
                    }
                    max = Math.max(max, left + mid + right);
                }
                dp[start][end] = max;
            }
        }
        return dp[0][len - 1];
    }
}
