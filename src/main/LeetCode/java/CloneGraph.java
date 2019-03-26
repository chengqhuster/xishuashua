package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/clone-graph/
 *
 * 思路简述：深度优先遍历 通过map维系克隆前后的node对应关系，以及处理被过的node信息
 *
 */

import LeetCode.java.DataStruct.Node;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    Map<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Node head = new Node();
        map.put(node, head);
        head.val = node.val;
        head.neighbors = cloneGraphCore(node);
        return head;
    }

    private List<Node> cloneGraphCore(Node node) {
        List<Node> res = new LinkedList<>();
        for (Node n : node.neighbors) {
            if (map.containsKey(n)) {
                res.add(map.get(n));
            } else {
                Node newNode = new Node();
                map.put(n, newNode);
                newNode.val = n.val;
                newNode.neighbors = cloneGraphCore(n);
                res.add(newNode);
            }
        }
        return res;
    }
}
