package LeetCode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目描述：https://leetcode.com/problems/map-sum-pairs/
 *
 * 思路简述：构建字典树
 */
public class MapSumPairs {

    private final PrefixSumTree root;
    private final char endOfStr;

    /** Initialize your data structure here. */
    public MapSumPairs() {
        root = new PrefixSumTree(0);
        endOfStr = '.';
    }

    public void insert(String key, int val) {
        List<PrefixSumTree> nodes = new ArrayList<>();
        nodes.add(root);
        PrefixSumTree node = root;
        for (int i = 0; i < key.length(); i++) {
            int keyValue = key.charAt(i) - 'a';
            if (!(node.sonNodes.containsKey(keyValue))) {
                node.sonNodes.put(keyValue, new PrefixSumTree(0));
            }
            node = node.sonNodes.get(keyValue);
            nodes.add(node);
        }
        int diff = val, targetValue = endOfStr - 'a';
        if (node.sonNodes.containsKey(targetValue)) {
            diff = val - node.sonNodes.get(targetValue).sum;
        }
        node.sonNodes.put(targetValue, new PrefixSumTree(val));

        // 重建统计数
        for (PrefixSumTree pathNode : nodes) {
            pathNode.addSum(diff);
        }
    }

    public int sum(String prefix) {
        PrefixSumTree node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int keyValue = prefix.charAt(i) - 'a';
            node = node.sonNodes.get(keyValue);
            if (node == null) {
                return 0;
            }
        }
        return node.sum;
    }

    class PrefixSumTree {
        int sum;
        Map<Integer, PrefixSumTree> sonNodes;

        public PrefixSumTree(int val) {
            sum = val;
            sonNodes = new HashMap<>();
        }

        public void addSum(int val) {
            sum += val;
        }
    }
}
