package LeetCode.java.DataStruct;

/*
 * 题目描述：https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * 思路简述：完成前缀树数据结构
 *
 */

import java.util.HashMap;
import java.util.Map;

public class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.childrens.containsKey(c)) {
                TrieNode newNode = new TrieNode();
                node.childrens.put(c, newNode);
            }
            node = node.childrens.get(c);
        }
        node.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode end = getEndNode(word);
        return end != null && end.endOfWord;
    }

    public boolean startsWith(String prefix) {
        return getEndNode(prefix) != null;
    }

    public TrieNode getEndNode(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!node.childrens.containsKey(c)) {
                return null;
            }
            node = node.childrens.get(c);
        }
        return node;
    }

    class TrieNode {
        Map<Character, TrieNode> childrens;
        boolean endOfWord;
        public TrieNode() {
            this.childrens = new HashMap<>();
            this.endOfWord = false;
        }
    }
}
