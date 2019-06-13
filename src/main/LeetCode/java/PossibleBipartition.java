package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/possible-bipartition/
 *
 * 思路简述：dfs
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PossibleBipartition {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        HashMap<Integer, GraphNode> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new GraphNode(i));
        }
        for (int[] pair : dislikes) {
            map.get(pair[0]).dislikes.add(map.get(pair[1]));
            map.get(pair[1]).dislikes.add(map.get(pair[0]));
        }
        boolean[] visited = new boolean[N + 1];
        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                setA.add(i);
                if (!dfs(map.get(i), setA, setB, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(GraphNode node, HashSet<Integer> cur, HashSet<Integer> nex, boolean[] visited) {
        //add first, then change visited state
        for (GraphNode n : node.dislikes) {
            if (cur.contains(n.val)) {
                return false;
            }
            nex.add(n.val);
        }
        for (GraphNode n : node.dislikes) {
            if (!visited[n.val]) {
                visited[n.val] = true;
                if (!dfs(n, nex, cur, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    class GraphNode {
        int val;
        List<GraphNode> dislikes = new ArrayList<>();

        GraphNode(int id) {
            this.val = id;
        }
    }
}
