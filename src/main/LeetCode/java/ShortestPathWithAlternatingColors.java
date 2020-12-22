package LeetCode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/shortest-path-with-alternating-colors/
 *
 * 思路简述：宽度遍历
 */
public class ShortestPathWithAlternatingColors {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> redMap = new HashMap<>();
        Map<Integer, List<Integer>> blueMap = new HashMap<>();

        for (int[] edge : red_edges) {
            redMap.compute(edge[0], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(edge[1]);

                return v;
            });
        }

        for (int[] edge : blue_edges) {
            blueMap.compute(edge[0], (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                }
                v.add(edge[1]);

                return v;
            });
        }

        // 从不同的颜色 visit 需要区别
        boolean[] redVisited = new boolean[2 * n];
        List<Integer> redNodes = new ArrayList<>();
        int[] redPathLen = new int[n];
        Arrays.fill(redPathLen, n * 2);
        redNodes.add(0);
        redVisited[0] = true;
        redNextCore(redVisited, redMap, blueMap, redNodes, 1, redPathLen);

        boolean[] blueVisited = new boolean[2 * n];
        List<Integer> blueNodes = new ArrayList<>();
        int[] bluePathLen = new int[n];
        Arrays.fill(bluePathLen, n * 2);
        blueNodes.add(0);
        blueVisited[0] = true;
        blueNextCore(blueVisited, redMap, blueMap, blueNodes, 1, bluePathLen);

        int[] res = new int[n];
        for (int i = 1; i < n; i++) {
            int pathLen = Math.min(redPathLen[i], bluePathLen[i]);
            res[i] = pathLen == 2 * n ? -1 : pathLen;
        }

        return res;
    }

    private void redNextCore(boolean[] visited,
                             Map<Integer, List<Integer>> redMap,
                             Map<Integer, List<Integer>> blueMap,
                             List<Integer> nodes,
                             int depth,
                             int[] pathLen) {
        int n = pathLen.length;
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        List<Integer> nextNodes = new ArrayList<>();
        for (int node : nodes) {
            if (redMap.containsKey(node)) {
                for (int nextNode : redMap.get(node)) {
                    if (!visited[nextNode]) {
                        visited[nextNode] = true;
                        pathLen[nextNode] = Math.min(depth, pathLen[nextNode]);
                        nextNodes.add(nextNode);
                    }
                }
            }
        }
        blueNextCore(visited, redMap, blueMap, nextNodes, depth + 1, pathLen);
    }

    private void blueNextCore(boolean[] visited,
                              Map<Integer, List<Integer>> redMap,
                              Map<Integer, List<Integer>> blueMap,
                              List<Integer> nodes,
                              int depth,
                              int[] pathLen) {
        int n = pathLen.length;
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        List<Integer> nextNodes = new ArrayList<>();
        for (int node : nodes) {
            if (blueMap.containsKey(node)) {
                for (int nextNode : blueMap.get(node)) {
                    if (!visited[nextNode + n]) {
                        visited[nextNode + n] = true;
                        pathLen[nextNode] = Math.min(depth, pathLen[nextNode]);
                        nextNodes.add(nextNode);
                    }
                }
            }
        }
        redNextCore(visited, redMap, blueMap, nextNodes, depth + 1, pathLen);
    }
}
