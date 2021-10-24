package LeetCode.java;

import java.util.*;

/**
 * 题目描述：https://leetcode.com/problems/design-search-autocomplete-system/
 *
 * 思路简述：字典树记录信息
 *
 */
public class DesignSearchAutocompleteSystem {

    private TireNode root;
    private String prefix;

    class TireNode {
        Map<Character, TireNode> children = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
    }

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TireNode();
        prefix = "";

        for (int i = 0; i < sentences.length; i++) {
            add(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix = prefix + c;
        TireNode node = root;
        for (char cc : prefix.toCharArray()) {
            node = node.children.get(cc);
            if (node == null) {
                return new ArrayList<>();
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue() - o1.getValue();
        });
        pq.addAll(node.count.entrySet());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            res.add(pq.poll().getKey());
        }
        return res;
    }

    private void add(String s, int count) {
        TireNode node = root;
        for (char c : s.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TireNode());
            }
            node = node.children.get(c);
            node.count.put(s, node.count.getOrDefault(s, 0) + count);
        }
    }
}
