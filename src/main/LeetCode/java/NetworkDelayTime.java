package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/network-delay-time/
 *
 * 思路简述：1. 最短路径问题，贪心算法
 *
 */

import java.util.HashMap;
import java.util.Map;

public class NetworkDelayTime {
    Map<Integer, GraphNode> map = new HashMap<>();

    public int networkDelayTime(int[][] times, int N, int K) {
        for (int i = 1; i <= N; i++) {
            map.put(i, new GraphNode(i));
        }
        for (int[] time : times) {
            map.get(time[0]).map.put(map.get(time[1]), time[2]);
        }
        HashMap<GraphNode, Integer> delayMap = new HashMap<>();
        delayMap.put(map.get(K), 0);
        while (true) {
            HashMap<GraphNode, Integer> candidate = new HashMap<>(8);
            for (Map.Entry<GraphNode, Integer> entry : delayMap.entrySet()) {
                for (Map.Entry<GraphNode, Integer> flow : entry.getKey().map.entrySet()) {
                    if (!delayMap.containsKey(flow.getKey())) {
                        if (!candidate.containsKey(flow.getKey())
                                || candidate.get(flow.getKey()) > entry.getValue() + flow.getValue()) {
                            candidate.put(flow.getKey(), entry.getValue() + flow.getValue());
                        }
                    }
                }
            }
            if (candidate.isEmpty()) {
                break;
            } else {
                int max = Integer.MAX_VALUE;
                GraphNode mark = null;
                for (GraphNode key : candidate.keySet()) {
                    if (candidate.get(key) < max) {
                        max = candidate.get(key);
                        mark = key;
                    }
                }
                delayMap.put(mark, candidate.get(mark));
            }
        }
        if (delayMap.size() != N) {
            return -1;
        } else {
            int res = 0;
            for (GraphNode key : delayMap.keySet()) {
                if (delayMap.get(key) > res) {
                    res = delayMap.get(key);
                }
            }
            return res;
        }
    }

    class GraphNode {
        int id;
        Map<GraphNode, Integer> map = new HashMap<>();

        GraphNode(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else {
                if (obj instanceof GraphNode) {
                    GraphNode node = (GraphNode)obj;
                    return node.id == id;
                } else {
                    return false;
                }
            }
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
