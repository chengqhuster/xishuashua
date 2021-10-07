package LeetCode.java;

import java.util.*;

/**
 * 题目描述：https://leetcode.com/problems/top-k-frequent-elements/
 *
 * 思路简述：先统计，再排序，使用优先队列降低复杂度 n*log(n) -> n*log(k)
 *         也可以使用桶排序
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
        // 直接使用桶排序
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }
        List<Integer> tpoKElements = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && tpoKElements.size() < k; i--) {
            if (bucket[i] != null) {
                tpoKElements.addAll(bucket[i]);
            }
        }
        return tpoKElements.stream().mapToInt(Integer::intValue).toArray();
//        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(numCountMap::get));
//        for (int num : numCountMap.keySet()) {
//            pq.add(num);
//            if (pq.size() > k) {
//                pq.poll();
//            }
//        }
//        int[] topK = new int[k];
//        for (int i = k - 1; i>= 0; i--) {
//            topK[i] = pq.poll();
//        }
//        return topK;
    }
}
