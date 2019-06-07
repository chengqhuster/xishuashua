package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/loud-and-rich/
 *
 * 思路简述：dfs
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoudAndRich {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        Map<Integer, GraphNode> map = new HashMap<>();
        int n = quiet.length;
        for (int i = 0; i < n; i++) {
            map.put(i, new GraphNode(i));
        }
        for (int[] r : richer) {
            map.get(r[1]).sons.add(map.get(r[0]));
        }
        // find all root node(least rich nodes)
        boolean[] state = new boolean[n];
        for (int[] r : richer) {
            state[r[0]] = true;
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < state.length; i++) {
            if (!state[i]) {
                dfs(map.get(i), quiet, res);
            }
        }
        return res;
    }

    int dfs(GraphNode node, int[] quiet, int[] res) {
        if (res[node.id] >= 0) {
            return res[node.id];
        }
        int min = node.id;
        for (GraphNode son : node.sons) {
            int temp = dfs(son, quiet, res);
            if (quiet[temp] < quiet[min]) {
                min = temp;
            }
        }
        res[node.id] = min;
        return min;
    }

    class GraphNode {
        int id;
        List<GraphNode> sons = new ArrayList<>();

        GraphNode(int id) {
            this.id = id;
        }
    }
}
