package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * 思路简述：利用map将字符串的索引位置与可能的子字符串对应起来，从左向右遍历即可
 *          先将words字符集用map映射到整数上，处理起来更加方便，注意words中的重复字符
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || words == null || words.length == 0 || words[0] == null ||
                words[0].length() == 0 || s.length() < words.length * words[0].length()) {
            return res;
        }
        int Len = words[0].length();
        HashMap<String, Integer> wordMap = new HashMap<>();
        // word -> integer
        int index = 0;
        for(int i=0; i<words.length; i++) {
            if(!wordMap.containsKey(words[i])) {
                wordMap.put(words[i], index);
                index++;
            }
        }
        // wordsState keep the number of each unique word
        int[] wordsState = new int[index];
        for(int i=0; i<words.length; i++) {
            wordsState[wordMap.get(words[i])]++;
        }
        // subStringMap keep the relationship of index and valid substring(in the words list)
        HashMap<Integer, Integer> subStringMap = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<=s.length() - Len; i++) {
            String sub = s.substring(i, i + Len);
            if(wordMap.containsKey(sub)) {
                queue.add(i);
                subStringMap.put(i, wordMap.get(sub));
            }
        }
        int[] cmp = new int[index];
        while(!queue.isEmpty()) {
            int start = queue.poll();
            int nextPos = start;
            int[] state = Arrays.copyOf(wordsState, index);
            while(subStringMap.containsKey(nextPos)) {
                state[subStringMap.get(nextPos)]--;
                if(state[subStringMap.get(nextPos)] < 0) {
                    break;
                } else {
                    if(Arrays.equals(state, cmp)) {
                        res.add(start);
                        break;
                    }
                }
                nextPos = nextPos + Len;
            }
        }
        return res;
    }
}
