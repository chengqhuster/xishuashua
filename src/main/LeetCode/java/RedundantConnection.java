package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/redundant-connection/description/
 *
 * 思路简述：给一棵树添加一条冗余的边，会在无向图内形成一个环路，去除度数为 1 的节点
 *          和它的边（重复），最后剩下来的即为环，环的任意一边即为冗余边
 *
 * 优化方向：重复去除度数为1的节点，不用每次都遍历，初始所有度数为1的节点放在队列中，
 *          每次去掉一个节点，如果它唯一的边对应的节点度数也为1，将对应节点加入队列，
 *          队列为空结束
 *
 * 更好的解：给出的二维数组代表节点的连通性，如果一组边对于图的连通性没有改变，那么它
 *         就会构成环路，也即为冗余的边，并查集很容易解决此类问题
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        if(edges == null || edges.length == 0) {
            return null;
        }

        // 用邻接表来表示图的拓扑
        Map<Integer, List<Integer>> adjacentMap = getAdjacentMap(edges);
        removeOneDegreeNode(adjacentMap);

        int index = -1;
        for(Map.Entry<Integer, List<Integer>> node : adjacentMap.entrySet()) {
            int posA = node.getKey();
            // 遍历圈中的边
            for(int posB : node.getValue()) {
                index = findEdgeIndex(edges, getSortedEdge(posA, posB), index);
            }
        }
        if(index > 0) {
            return edges[index];
        } else {
            return null;
        }
    }

    // 由表示边的二位矩阵得到邻接图
    private Map<Integer, List<Integer>> getAdjacentMap(int[][] edges) {
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
        for(int i=0; i<edges.length; i++) {
            int[] v = edges[i];
            if(!adjacentMap.containsKey(v[0])) {
                adjacentMap.put(v[0], new ArrayList<>());
            }
            adjacentMap.get(v[0]).add(v[1]);
            if(!adjacentMap.containsKey(v[1])) {
                adjacentMap.put(v[1], new ArrayList<>());
            }
            adjacentMap.get(v[1]).add(v[0]);
        }
        return adjacentMap;
    }

    // 反复的去掉度数为 1 的节点，剩下一个圆
    private void removeOneDegreeNode( Map<Integer, List<Integer>> adjacentMap) {
        boolean flag = true;
        while(flag) {
            flag = false;
            Set<Integer> removeNodes = new HashSet<>();
             for(Map.Entry<Integer, List<Integer>> node : adjacentMap.entrySet()) {
                 if(node.getValue().size() == 1) {
                     flag = true;
                     adjacentMap.get(node.getValue().get(0)).remove(node.getKey());
                     removeNodes.add(node.getKey());
                 }
            }
            for(Integer key : removeNodes) {
                adjacentMap.remove(key);
            }
        }
    }

    // 符合形式的边
    private int[] getSortedEdge(int a, int b) {
        int[] edge = new int[2];
        if(a > b) {
            edge[0] = b;
            edge[1] = a;
        } else {
            edge[0] = a;
            edge[1] = b;
        }
        return edge;
    }

    // 找出边在二位矩阵的位置，返回较大的下标
    private int findEdgeIndex(int[][] edges, int[] v, int index) {
        for(int i=0; i<edges.length; i++) {
            if(edges[i][0] == v[0] && edges[i][1] == v[1] && i > index) {
                return i;
            }
        }
        return index;
    }
}
