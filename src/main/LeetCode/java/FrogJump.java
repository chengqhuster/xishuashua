package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/frog-jump/description/
 *
 * 思路简述：动态规划 每个节点（步骤）需要知道到达自身时青蛙上一次跳的步数[集合]（状态）
 *          从第一步开始跳跃 1 步（起始条件），后续每次能跳 k-1, k, k+1 步（状态转移）
 *          每次跳跃时将往可能跳至的目标点集合内加入此次跳跃距离，跳至终点结束
 */

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrogJump {
    public boolean canCross(int[] stones) {
        if(stones == null || stones.length == 0) {
            return false;
        }
        LinkedHashMap<Integer, HashSet<Integer>> map = new LinkedHashMap<>();
        for(int i=0; i<stones.length; i++) {
            map.put(stones[i], new HashSet<>());
        }
        int target = stones[stones.length - 1];
        for(Map.Entry<Integer, HashSet<Integer>> entry  : map.entrySet()) {
            int startPoint = entry.getKey();

            // 第一步只能跳 1 步数
            if(entry.getKey() == stones[0]) {
                if(jumpTry(startPoint, 1, map) == target) {
                    return true;
                }
                continue;
            }

            // 其他情况跳 k-1, k , k+1 步数
            for(int step : entry.getValue()) {
                int jumpA = step - 1;
                if(jumpA > 0 && jumpTry(startPoint, jumpA, map) == target) {
                    return true;
                }
                if(jumpTry(startPoint, step, map) == target) {
                    return true;
                }
                if(jumpTry(startPoint, step + 1, map) == target) {
                    return true;
                }
            }
        }
        return false;
    }

    // 跳跃成功时，返回落点
    private int jumpTry(int startPoint, int step, LinkedHashMap<Integer, HashSet<Integer>> map) {
        int endPoint = startPoint + step;
        if(map.containsKey(endPoint)) {
            map.get(endPoint).add(step);
            return endPoint;
        }
        return 0;
    }
}
