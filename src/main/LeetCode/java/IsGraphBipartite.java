package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/is-graph-bipartite/
 *
 * 思路简述：dfs or bfs(using queue)
 *
 */

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return true;
        }
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !isBipartiteCore(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBipartiteCore(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }
        else {
            colors[node] = color;
            for (int neighbour : graph[node]) {
                if (!isBipartiteCore(graph, colors, -color, neighbour)) {
                    return false;
                }
            }
            return true;
        }
    }
}
