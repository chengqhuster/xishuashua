package LeetCode.java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 题目描述：https://leetcode.com/problems/top-k-frequent-elements/
 *
 * 思路简述：先统计，再排序，使用优先队列降低复杂度 n*log(n) -> n*log(k)
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.compute(num, (n, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(numCountMap::get));
        for (int num : numCountMap.keySet()) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] topK = new int[k];
        for (int i = k - 1; i>= 0; i--) {
            topK[i] = pq.poll();
        }
        return topK;
    }
}
