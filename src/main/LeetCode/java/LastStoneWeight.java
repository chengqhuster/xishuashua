package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/last-stone-weight/
 *
 * 思路简述：最大堆，PriorityQueue 默认是自然排序最小堆
 *
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            if (a != b) {
                queue.add(Math.abs(a  -b));
            }
        }
        if (queue.isEmpty()) {
            return 0;
        } else {
            return queue.peek();
        }
    }
}
