package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/concatenated-words/
 *
 * 思路简述：先按照字符串长度排序，然后逐个添加到Tire（前缀树）中，添加之前通过 judge 函数判断
 *          是否存在满足要求的拼接字符串
 *
 */

import LeetCode.java.DataStruct.Trie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        Arrays.sort(words, Comparator.comparing(String::length));
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            if (judge(trie, words[i])) {
                res.add(words[i]);
            }
            trie.insert(words[i]);
        }
        return res;
    }

    private boolean judge(Trie trie, String word) {
        for (int j = 0; j < word.length() - 1; j++) {
            String sub = word.substring(0, j + 1);
            if (trie.startsWith(sub)) {
                if (trie.search(sub) && (trie.search(word.substring(j + 1)) || judge(trie, word.substring(j + 1)))) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
