package LeetCode.java;

/*
 * 题目描述：https://leetcode.com/problems/number-of-matching-subsequences/
 *
 * 思路简述：继承 IsSubsequence 思路
 *
 */

import java.util.HashSet;
import java.util.Set;

public class NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String s, String[] words) {
        int[] index = new int[words.length];
        int[] target = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            target[i] = words[i].length();
        }
        int res = 0;
        // 减少重复计算
        Set<Character> ignore = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (ignore.contains(s.charAt(i))) {
                continue;
            }
            int count = 0;
            for (int j = 0; j < words.length; j++) {
                if (index[j] < target[j]) {
                    if (s.charAt(i) == words[j].charAt(index[j])) {
                        count++;
                        index[j]++;
                        if (index[j] == target[j]) {
                            res++;
                        }
                    }
                }
            }
            if (count == 0) {
                ignore.add(s.charAt(i));
            } else {
                ignore.clear();
            }
        }
        return res;
    }
}
