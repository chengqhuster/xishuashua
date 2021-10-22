package LeetCode.java;

/**
 * 题目描述：https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 *
 * 思路简述：使用并查集计算union区域数量
 *
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {

        int[] unionArray = new int[n];
        for (int i = 0; i < n; i++) {
            unionArray[i] = i;
        }
        int res = n;
        for (int[] edge : edges) {
            if (union(unionArray, edge[0], edge[1])) {
                res--;
            }
        }
        return res;
    }

    private boolean union(int[] unionArray, int a, int b) {
        int aRoot = findRoot(unionArray, a);
        int bRoot = findRoot(unionArray, b);
        if (aRoot != bRoot) {
            unionArray[aRoot] = bRoot;
            return true;
        }
        return false;
    }

    private int findRoot(int[] unionArray, int a) {
        while (unionArray[a] != a) {
            unionArray[a] = unionArray[unionArray[a]];
            a = unionArray[a];
        }
        return a;
    }
}
