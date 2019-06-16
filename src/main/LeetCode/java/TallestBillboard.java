package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/tallest-billboard/
 *
 * 思路简述：难点在于如何构建dp
 *          dp[a] 代表两个子集和的差为 a 时候的“最高”解，eg dp[diff] = a 说明最高的组合为(a, a + diff)
 *
 */

import java.util.HashMap;

public class TallestBillboard {
    public int tallestBillboard(int[] rods) {
        if (rods == null || rods.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int x : rods) {
            HashMap<Integer, Integer> copy = new HashMap<>(dp);
            for (int diff : copy.keySet()) {
                dp.put(diff + x, Math.max(copy.get(diff), dp.getOrDefault(diff + x, 0)));
                dp.put(Math.abs(x - diff), Math.max(copy.get(diff) + Math.min(x, diff), dp.getOrDefault(Math.abs(x - diff), 0)));
            }
        }
        return dp.get(0);
    }
}
