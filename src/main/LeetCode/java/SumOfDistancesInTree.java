package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/sum-of-distances-in-tree/
 *
 * 思路简述：1. 用一条有方向的边 i 到 j 将图分成两个部分
 *          edge[i][j][0] 代表 i 往 j 方向的一侧累计多少个节点 a
 *          edge[i][j][1] ..................一侧节点到 j 的距离和 b
 *          从图的边缘（只有一个邻居节点）开始，dfs的方式计算出所有边的 a b 值
 *          下面的 v 矩阵即为上述定义的参数，同时也起到了 memory 的作用
 *          dfs函数描述了怎么用一个节点的其它入边求特定出边的参数值
 *          最终结果 Time Limit Exceeded
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SumOfDistancesInTree {
    public int[] sumOfDistancesInTreeTLE(int N, int[][] edges) {
        int[] res = new int[N];
        HashMap<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new GraphNode(i));
        }
        for (int[] edge : edges) {
            map.get(edge[0]).neighbours.add(map.get(edge[1]));
            map.get(edge[1]).neighbours.add(map.get(edge[0]));
        }
        // find all one neighbour nodes
        List<GraphNode> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (map.get(i).neighbours.size() == 1) {
                nodes.add(map.get(i));
            }
        }
        int[][][] v = new int[N][N][2];
        for (GraphNode node : nodes) {
            dfs(node.neighbours.get(0).val, node.val, map, v);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[i] += v[j][i][1];
            }
        }
        return res;
    }

    private int[] dfs(int i, int j, HashMap<Integer, GraphNode> map, int[][][] v) {
        if (v[i][j][0] > 0) {
            return v[i][j];
        }
        v[i][j][0] = 1;
        v[i][j][1] = 1;
        for (GraphNode node : map.get(i).neighbours) {
            // j is the output node
            if (node.val != j) {
                int[] temp = dfs(node.val, i, map, v);
                v[i][j][0] += temp[0];
                v[i][j][1] += temp[0] + temp[1];
            }
        }
        return v[i][j];
    }

    class GraphNode {
        int val;
        List<GraphNode> neighbours = new ArrayList<>();

        GraphNode(int val) {
            this.val = val;
        }
    }
}
