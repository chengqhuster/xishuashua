package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/can-i-win/
 *
 * 思路简述：dfs 暴力求解，需要用 map 保存中间解 减少计算时间
 *
 */

import java.util.HashMap;
import java.util.Map;

public class CanIWin {
    boolean[] state;
    Map<Integer, Boolean> map;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        if (desiredTotal > maxChoosableInteger * (maxChoosableInteger + 1) / 2) {
            return false;
        }
        state = new boolean[maxChoosableInteger];
        map = new HashMap<>();
        return dfs(desiredTotal);
    }

    // state[i] = false 从 A 换到 B 处，能够实现解
    private boolean dfs(int total) {
        int key = format(state);
        if (!map.containsKey(key)) {
            for (int i = 0; i < state.length; i++) {
                if (!state[i]) {
                    state[i] = true;
                    if (total <= i + 1 || !dfs(total -i - 1)) {
                        map.put(key, true);
                        state[i] = false;
                        return true;
                    }
                    // B
                    state[i] = false;
                }
                // A
            }
            map.put(key, false);
        }
        return map.get(key);
    }

    private int format(boolean[] state) {
        int res = 0;
        int num = 1;
        for (int i = 0; i < state.length; i++) {
            if (state[i]) {
                res += num;
            }
            num = num << 1;
        }
        return res;
    }
}
