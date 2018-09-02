package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/redundant-connection-ii/description/
 *
 * 思路简述：树的有向图结构，除根节点所有节点入度均为1，根节点入度为0，添加一条有向
 *          边后，或者所有节点入度均变为1（冗余边指向根节点），或者存在入度为2的节点
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if(edges == null || edges.length == 0) {
            return null;
        }
        Map<Integer, ArrayList<LinkedList<Integer>>> adjacentMap = getAdjacentMap(edges);

        int degree2Node = -1;
        for(Map.Entry<Integer, ArrayList<LinkedList<Integer>>> node : adjacentMap.entrySet()) {
            if(node.getValue().get(1).size() == 2) {
                degree2Node = node.getKey();
                break;
            }
        }
        // 存在入度为2的节点
        if(degree2Node >= 0) {
            LinkedList<Integer> inNodeList = adjacentMap.get(degree2Node).get(1);
            for(Integer outNode : adjacentMap.get(degree2Node).get(0)) {
                // 出节点与入节点有重合，返回该双向边上的冗余边
                if(outNode == inNodeList.get(0) || outNode == inNodeList.get(1)) {
                    return new int[]{outNode, degree2Node};
                }
            }
            for(int i=0; i<2; i++) {
                // 入节点的入度为0且出度为1，则另外一条入边为冗余边
                if(adjacentMap.get(inNodeList.get(i)).get(1).size() == 0 &&
                        adjacentMap.get(inNodeList.get(i)).get(0).size() == 1) {
                    int resNode = adjacentMap.get(degree2Node).get(1).get(1 - i);
                    return new int[]{resNode, degree2Node};
                }
            }
            // 否则删除任意一个入边
            int pos = -1;
            for(Integer inNode : adjacentMap.get(degree2Node).get(1)) {
                int[] v = new int[]{inNode, degree2Node};
                pos = findEdgeIndex(edges, v, pos);
            }
            if(pos > 0) {
                return edges[pos];
            } else {
                return null;
            }
        }
        int pos = -1;
        // 入度均为1，有向图中存在环路，去掉出度为0的节点
        remove0OutDegreeNodes(adjacentMap);
        // 剩余为环路，去掉任意一条边即可
        for(Map.Entry<Integer, ArrayList<LinkedList<Integer>>> node : adjacentMap.entrySet()) {
            int inNode = node.getValue().get(1).get(0);
            int[] v = new int[]{inNode, node.getKey()};
            pos = findEdgeIndex(edges, v, pos);
        }
        if(pos > 0) {
            return edges[pos];
        } else {
            return null;
        }
    }

    // map结构记录图的邻接关系，List数组分别记录入节点和出节点
    private Map<Integer, ArrayList<LinkedList<Integer>>> getAdjacentMap(int[][] edges) {
        Map<Integer, ArrayList<LinkedList<Integer>>> adjacentMap = new HashMap<>();
        for(int[] v : edges) {
            if(!adjacentMap.containsKey(v[0])) {
                ArrayList<LinkedList<Integer>> nodeList = new ArrayList<>();
                nodeList.add(new LinkedList<>());
                nodeList.add(new LinkedList<>());
                adjacentMap.put(v[0], nodeList);
            }
            // 出节点
            adjacentMap.get(v[0]).get(0).add(v[1]);
            if(!adjacentMap.containsKey(v[1])) {
                ArrayList<LinkedList<Integer>> nodeList = new ArrayList<>();
                nodeList.add(new LinkedList<>());
                nodeList.add(new LinkedList<>());
                adjacentMap.put(v[1], nodeList);
            }
            // 入节点
            adjacentMap.get(v[1]).get(1).add(v[0]);
        }
        return adjacentMap;
    }

    private int findEdgeIndex(int[][] edges, int[] v, int pos) {
        for(int i=0; i<edges.length; i++) {
            if(edges[i][0] == v[0] && edges[i][1] == v[1] && i > pos) {
                return i;
            }
        }
        return pos;
    }

    // 去掉出度为0的节点
    private void remove0OutDegreeNodes(Map<Integer, ArrayList<LinkedList<Integer>>> adjacentMap) {
        // 用队列实现
        LinkedList<Integer> delectQueue = new LinkedList<>();
        for(Map.Entry<Integer, ArrayList<LinkedList<Integer>>> node : adjacentMap.entrySet()) {
            if(node.getValue().get(0).size() == 0) {
                delectQueue.offer(node.getKey());
            }
        }
        while(delectQueue.size() > 0) {
            int node = delectQueue.poll();
            // 记录父节点
            int parentNode = adjacentMap.get(node).get(1).get(0);
            // 删除节点
            adjacentMap.remove(node);
            adjacentMap.get(parentNode).get(0).remove(Integer.valueOf(node));
            // 父节点出度也为0
            if(adjacentMap.get(parentNode).get(0).size() == 0) {
                delectQueue.offer(parentNode);
            }
        }
    }

    public static void main(String[] args) {
        int[][] intput = {{5,2}, {5,1}, {3,1}, {3,4}, {3,5}};
        System.out.println(new RedundantConnectionII().findRedundantDirectedConnection(intput));
    }
}
