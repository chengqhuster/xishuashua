package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * 思路简述：字典树，前缀树
 *
 */

import java.util.HashMap;
import java.util.Map;


public class AddAndSearchWord {
    TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.childrens.containsKey(c)) {
                node.childrens.put(c, new TrieNode());
            }
            node = node.childrens.get(c);
        }
        node.endFlag = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchCore(word, 0, root);
    }

    private boolean searchCore(String word, int index, TrieNode root) {
        TrieNode node = root;
        for (int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != '.') {
                node = node.childrens.get(c);
                if (node == null) {
                    return false;
                }
            } else {
                for (Map.Entry<Character, TrieNode> entry : node.childrens.entrySet()) {
                    if (searchCore(word, i + 1, entry.getValue())) {
                        return true;
                    }
                }
                return false;
            }
        }
        return node.endFlag;
    }

    class TrieNode {
        Map<Character, TrieNode> childrens;
        boolean endFlag;

        public TrieNode() {
            this.childrens = new HashMap<>();
            this.endFlag = false;
        }

    }
}
