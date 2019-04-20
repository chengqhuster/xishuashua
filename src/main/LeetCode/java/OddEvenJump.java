package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/odd-even-jump/
 *
 * 思路简述：dp[i][0] 代表在 i 处开始偶数跳是否能够到达终点，dp[i][1] 表示该处的奇数跳情况
 *          dp[i][0] = dp[target][1]
 *          dp[i][1] = dp[target][0]
 *          target 为 i 的奇偶跳的下一个节点位置（用 TreeMap 保存 i 后续的节点信息，快速定位 target）
 *          显然是从后往前的 dp 过程，初始条件 dp[len - 1][0] = true; dp[len - 1][1] = true
 *
 */

import java.util.Map;
import java.util.TreeMap;

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int len = A.length;
        boolean[][] dp = new boolean[len][2];
        dp[len - 1][0] = true;
        dp[len - 1][1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[len - 1], len - 1);
        for (int i = len - 2; i >= 0; i--) {
            if (map.containsKey(A[i])) {
                int targetIndex = map.get(A[i]);
                dp[i][0] = dp[targetIndex][1];
                dp[i][1] = dp[targetIndex][0];
            } else {
                Map.Entry<Integer, Integer> entry = map.ceilingEntry(A[i]);
                if (entry == null) {
                    dp[i][1] = false;
                } else {
                    dp[i][1] = dp[entry.getValue()][0];
                }
                entry = map.floorEntry(A[i]);
                if (entry == null) {
                    dp[i][0] = false;
                } else {
                    dp[i][0] = dp[entry.getValue()][1];
                }
            }
            map.put(A[i], i);
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (dp[i][1]) {
                count++;
            }
        }
        return count;
    }
}
