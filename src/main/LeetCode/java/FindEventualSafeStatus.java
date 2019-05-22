package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/shopping-offers/
 *
 * 思路简述：1. 判断每个节点，从该节点出发无环，则为所求节点，复杂度高
 *          2. 从已有结果集(出度为 0 的点)出发，往结果集节点的父节点遍历计算
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FindEventualSafeStatus {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            map.put(i, new GraphNode(i));
            if (graph[i].length == 0) {
                res.add(i);
            }
        }
        // build graph
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                map.get(i).sons.add(graph[i][j]);
                map.get(graph[i][j]).fathers.add(i);
            }
        }
        int nextNode;
        do {
            nextNode = getNextNode(res, map);
            if (nextNode >= 0) {
                res.add(nextNode);
            }
        } while (nextNode >= 0);
        Collections.sort(res);
        return res;
    }

    private int getNextNode(List<Integer> res, HashMap<Integer, GraphNode> map) {
        for (Integer n : res) {
            GraphNode node = map.get(n);
            for (Integer m : node.fathers) {
                if (res.contains(m)) {
                    continue;
                }
                GraphNode fa = map.get(m);
                if (res.containsAll(fa.sons)) {
                    return m;
                }
            }
        }
        return -1;
    }

    class GraphNode {
        List<Integer> fathers;
        List<Integer> sons;
        int val;

        GraphNode(int val) {
            this.val = val;
            fathers = new ArrayList<>();
            sons = new ArrayList<>();
        }
    }
}
