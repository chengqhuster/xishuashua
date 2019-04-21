package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/the-skyline-problem/
 *
 * 思路简述：最大堆保存当前位置的最高高度（发生变化时记录节点）
 *          将 buildings 转化为高度数组，左界的高度取负值，排序时按照先位置再高度的优先级排序
 *          这样遍历时 负高度入列，正高度出列，且同一位置先处理负高度
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        if (buildings == null) {
            return res;
        }
        for (int[] building: buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }
        Collections.sort(heights, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        // max heap
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        queue.offer(0);
        int last = 0;
        for (int[] height : heights) {
            if (height[1] < 0) {
                queue.offer(-height[1]);
            } else {
                queue.remove(height[1]);
            }
            int cur = queue.peek();
            if (last != cur) {
                res.add(new int[]{height[0], cur});
                last = cur;
            }
        }
        return res;
    }
}
